package com.week1.assignment.service;

import com.week1.assignment.entity.Basket;
import com.week1.assignment.entity.BasketItem;
import com.week1.assignment.entity.Product;
import com.week1.assignment.entity.User;
import com.week1.assignment.model.*;
import com.week1.assignment.repository.BasketRepository;
import com.week1.assignment.repository.ProductRepository;
import com.week1.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class BasketService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ProductRepository productRepository;

    public BasketService(
            UserRepository userRepository,
            BasketRepository basketRepository,
            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public BasketCreateEnquiryResponseDTO createBasket(BasketCreateRequestDTO basketCreateRequestDTO) {

        Optional<User> user = userRepository.findByUserName(basketCreateRequestDTO.getUserName());
        List<Integer> ids = basketCreateRequestDTO.getProductList()
                .stream()
                .map(ProductQuantityRequestDTO::getProductId).collect(Collectors.toList());
        List<Product> productQueryList = productRepository.findByIds(ids);

        List<BasketItem> basketItemList = new ArrayList<>();

        productQueryList.forEach(productQuery -> {
            BasketItem basketItem = new BasketItem();
            basketItem.setProduct(productQuery);
            Optional<ProductQuantityRequestDTO> productRequestDTO = basketCreateRequestDTO.getProductList()
                    .stream()
                    .filter(productRequest ->
                            productRequest.getProductId().equals(productQuery.getId()))
                    .findFirst();
            basketItem.setQuantity(productRequestDTO.map(ProductQuantityRequestDTO::getQuantity).orElse(0.0));
            basketItem.setSize(productRequestDTO.map(ProductQuantityRequestDTO::getSize).orElse(0.0));
            basketItemList.add(basketItem);
        });

        Basket basket = new Basket();
        basket.setBasketItem(basketItemList);
        basket.setTotal(calculateProductAmount(basketCreateRequestDTO, productQueryList));
        basket.setUser(user.orElse(null));

        return transformToBasketResponseDTO(basketRepository.save(basket));
    }

    private BasketCreateEnquiryResponseDTO transformToBasketResponseDTO(Basket basket) {
        BasketCreateEnquiryResponseDTO basketCreateEnquiryResponseDTO = new BasketCreateEnquiryResponseDTO();

        basketCreateEnquiryResponseDTO.setBasketId(basket.getId());
        basketCreateEnquiryResponseDTO.setTotal(basket.getTotal());

        basketCreateEnquiryResponseDTO.setBasketItems(
                transformToBasketItemDTO(basket.getBasketItem()));
        return basketCreateEnquiryResponseDTO;
    }

    private List<BasketItemDTO> transformToBasketItemDTO(List<BasketItem> basketItems) {
        List<BasketItemDTO> basketItemDTOList = new ArrayList<>();

        basketItems.forEach(item -> {
            BasketItemDTO basketItemDTO = new BasketItemDTO();
            basketItemDTO.setBasketItemId(item.getId());
            basketItemDTO.setProductDetail(transformToProductSelectedDetailDTO(item.getProduct()));
            basketItemDTO.setQuantity(item.getQuantity());
            basketItemDTO.setSize(item.getSize());
            basketItemDTOList.add(basketItemDTO);
        });

        return basketItemDTOList;
    }

    private ProductSelectedDetailDTO transformToProductSelectedDetailDTO(Product product) {
        ProductSelectedDetailDTO productSelectedDetailDTO = new ProductSelectedDetailDTO();

        productSelectedDetailDTO.setProductId(product.getId());
        productSelectedDetailDTO.setName(product.getName());
        productSelectedDetailDTO.setDescription(product.getDescription());
        productSelectedDetailDTO.setPrice(product.getPrice());
        productSelectedDetailDTO.setDiscount(product.getDiscount());
        productSelectedDetailDTO.setFullPrice(product.getFullPrice());
        productSelectedDetailDTO.setShop(product.getShop());
        productSelectedDetailDTO.setImage(product.getImage());
        productSelectedDetailDTO.setCreatedAt(product.getCreatedAt());
        productSelectedDetailDTO.setWarranty(product.getWarranty());
        productSelectedDetailDTO.setPromotionStart(product.getPromotionStart());
        productSelectedDetailDTO.setPromotionEnd(product.getPromotionEnd());

        return productSelectedDetailDTO;
    }

    private Double calculateProductAmount(BasketCreateRequestDTO basketCreateRequestDTO, List<Product> productQueryList) {

        AtomicReference<Double> totalAmount = new AtomicReference<>(0.0);

        productQueryList.forEach(productQuery -> {
            Double sum = basketCreateRequestDTO.getProductList()
                    .stream()
                    .mapToDouble(productRequest -> {
                        if (productRequest.getProductId().equals(productQuery.getId())) {
                            return (productQuery.getPrice() * productRequest.getQuantity());
                        }
                        return 0.0;
                    }).sum();
            totalAmount.set(totalAmount.get() + sum);
        });

        return totalAmount.get();
    }


    public List<BasketCreateEnquiryResponseDTO> findBasketListByBasketIdAndUserName(BasketEnquiryRequestDTO basketEnquiryRequest) {
        List<Integer> basketIds = basketEnquiryRequest.getBasketList()
                .stream()
                .map(BasketDTO::getBasketId).collect(Collectors.toList());
        List<Basket> result = basketRepository.findByBasketIdAndUserName(
                basketIds,
                basketEnquiryRequest.getUserName());

        return transformToBasketListResponseDTO(result);
    }

    private List<BasketCreateEnquiryResponseDTO> transformToBasketListResponseDTO(List<Basket> baskets) {

        List<BasketCreateEnquiryResponseDTO> basketCreateEnquiryResponseList = new ArrayList<>();
        baskets.forEach(basket -> basketCreateEnquiryResponseList.add(transformToBasketResponseDTO(basket)));
        return basketCreateEnquiryResponseList;
    }
}
