package com.week1.assignment.service;

import com.week1.assignment.entity.Brand;
import com.week1.assignment.entity.Product;
import com.week1.assignment.entity.ProductAvailable;
import com.week1.assignment.exception.ProductNotFoundException;
import com.week1.assignment.model.*;
import com.week1.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductInfoResponseDTO> findByName(Optional<String> productName) {
        List<Product> productList = productRepository.findByNameContainingIgnoreCase(productName.get());
        if (!productList.isEmpty()) {
            List<ProductInfoResponseDTO> productInfoResponseDTOList = new ArrayList<>();
            productList.forEach(product -> {
                ProductInfoResponseDTO productInfoResponseDTO = new ProductInfoResponseDTO();
                productInfoResponseDTO = transformToProductInfoDTO(product);
                productInfoResponseDTOList.add(productInfoResponseDTO);
            });
            return productInfoResponseDTOList;
        }
        throw new ProductNotFoundException(MessageFormat.format("Product name: {0} not found", productName.get()));
    }

    public ProductDetailResponseDTO findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isEmpty()) {
            return transformProductToProductDetailResponse(product.get());
        }
        throw new ProductNotFoundException(MessageFormat.format("Product id: {0} not found", id));
    }

    private ProductDetailResponseDTO transformProductToProductDetailResponse(Product product) {

        ProductDetailResponseDTO productDetailResponseDTO = new ProductDetailResponseDTO();

        if (product != null) {
            productDetailResponseDTO.setId(product.getId());
            productDetailResponseDTO.setName(product.getName());
            productDetailResponseDTO.setDescription(product.getDescription());
            productDetailResponseDTO.setPrice(product.getPrice());
            productDetailResponseDTO.setDiscount(product.getDiscount());
            productDetailResponseDTO.setFullPrice(product.getFullPrice());
            productDetailResponseDTO.setShop(product.getShop());
            productDetailResponseDTO.setImage(product.getImage());
            productDetailResponseDTO.setCreatedAt(product.getCreatedAt());
            productDetailResponseDTO.setWarranty(product.getWarranty());
            productDetailResponseDTO.setPromotionStart(product.getPromotionStart());
            productDetailResponseDTO.setPromotionEnd(product.getPromotionEnd());

            productDetailResponseDTO.setProductAvailableList(
                    transformToProductAvailableDTO(product.getProductAvailableList()));

            productDetailResponseDTO.setBrand(
                    transformToBrandDTO(product.getBrand()));
        }

        return productDetailResponseDTO;

    }

    private List<ProductAvailableDTO> transformToProductAvailableDTO(List<ProductAvailable> productAvailableList) {

        List<ProductAvailableDTO> productAvailableDTOList = new ArrayList<>();

        if (productAvailableList != null) {
            productAvailableList.forEach(productAvailable -> {
                ProductAvailableDTO productAvailableDTO = new ProductAvailableDTO();
                productAvailableDTO.setId(productAvailable.getId());
                productAvailableDTO.setSize(productAvailable.getSize());
                productAvailableDTO.setQuantity(productAvailable.getQuantity());
                productAvailableDTO.setCreatedAt(productAvailable.getCreatedAt());
                productAvailableDTOList.add(productAvailableDTO);
            });
        }
        return productAvailableDTOList;
    }

    private BrandDTO transformToBrandDTO(Brand brand) {

        BrandDTO brandDTO = new BrandDTO();

        if (brand != null) {
            brandDTO.setId(brand.getId());
            brandDTO.setName(brand.getName());
            brandDTO.setDescription(brand.getDescription());
            brandDTO.setCreatedAt(brand.getCreatedAt());
        }
        return brandDTO;
    }

    private ProductInfoResponseDTO transformToProductInfoDTO(Product product) {

        ProductInfoResponseDTO productInfoResponseDTO = new ProductInfoResponseDTO();

        if (product != null) {
            productInfoResponseDTO.setId(product.getId());
            productInfoResponseDTO.setName(product.getName());
            productInfoResponseDTO.setDescription(product.getDescription());
            productInfoResponseDTO.setPrice(product.getPrice());
            productInfoResponseDTO.setDiscount(product.getDiscount());
            productInfoResponseDTO.setFullPrice(product.getFullPrice());
            productInfoResponseDTO.setShop(product.getShop());
            productInfoResponseDTO.setImage(product.getImage());
            productInfoResponseDTO.setCreatedAt(product.getCreatedAt());
            productInfoResponseDTO.setWarranty(product.getWarranty());
            productInfoResponseDTO.setPromotionStart(product.getPromotionStart());
            productInfoResponseDTO.setPromotionEnd(product.getPromotionEnd());
        }
        return productInfoResponseDTO;
    }
}
