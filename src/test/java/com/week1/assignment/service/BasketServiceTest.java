package com.week1.assignment.service;

import com.week1.assignment.entity.Basket;
import com.week1.assignment.entity.BasketItem;
import com.week1.assignment.entity.Product;
import com.week1.assignment.entity.User;
import com.week1.assignment.model.*;
import com.week1.assignment.repository.BasketRepository;
import com.week1.assignment.repository.ProductRepository;
import com.week1.assignment.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private BasketRepository basketRepositoryMock;
    @Mock
    private ProductRepository productRepositoryMock;

    @Test()
    @DisplayName("create basket ด้วย request: " +
            " userName = test, " +
            " productId = 1, quantity = 1, size = 1.0 " +
            " แล้วจะต้องได้ basketResponse" +
            " productId = 1, quantity = 1, size = 1.0 ")
    void caseCreateBasket() {

        // Arrange
        User user = new User();
        user.setUserName("test");
        when(userRepositoryMock.findByUserName("test")).thenReturn(Optional.of(user));

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setPrice(100.0);
        productList.add(product);
        when(productRepositoryMock.findByIds(any())).thenReturn(productList);

        Basket basketResponse = new Basket();
        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem basketItem = new BasketItem();
        basketItem.setQuantity(1.0);
        basketItem.setSize(1.0);
        basketItem.setProduct(product);
        basketItems.add(basketItem);

        basketResponse.setBasketItem(basketItems);
        basketResponse.setId(1);
        basketResponse.setTotal(1.0);
        basketResponse.setUser(user);
        when(basketRepositoryMock.save(any())).thenReturn(basketResponse);

        // Act
        BasketCreateRequestDTO basketCreateRequestDTO = new BasketCreateRequestDTO();
        basketCreateRequestDTO.setUserName("test");

        List<ProductQuantityRequestDTO> productRequestList = new ArrayList<>();
        ProductQuantityRequestDTO productRequest = new ProductQuantityRequestDTO();
        productRequest.setProductId(1);
        productRequest.setQuantity(1.0);
        productRequest.setSize(1.0);
        productRequestList.add(productRequest);

        basketCreateRequestDTO.setProductList(productRequestList);
        BasketService basketService = new BasketService(userRepositoryMock, basketRepositoryMock, productRepositoryMock);
        BasketCreateEnquiryResponseDTO basketCreateResponse = basketService.createBasket(basketCreateRequestDTO);

        // Assert
        assertEquals(basketCreateResponse.getBasketItems().get(0).getProductDetail().getProductId(), 1);
        assertEquals(basketCreateResponse.getBasketItems().get(0).getQuantity(), 1.0);
        assertEquals(basketCreateResponse.getBasketItems().get(0).getSize(), 1.0);
    }

    @Test
    @DisplayName("find basket ด้วย : " +
            " userName = test, " +
            " basketItemId = 1 " +
            " แล้วจะต้องได้ basketResponse" +
            " basketItemId = 1")
    void findBasketListByBasketIdAndUserName() {
        // Arrange
        Product product = new Product();
        product.setId(1);

        List<BasketItem> basketItemList = new ArrayList<>();
        BasketItem basketItem = new BasketItem();
        basketItem.setId(1);
        basketItem.setProduct(product);
        basketItemList.add(basketItem);

        Basket basket = new Basket();
        basket.setId(1);
        basket.setBasketItem(basketItemList);

        List<Basket> basketList = new ArrayList<>();
        basketList.add(basket);
        when(basketRepositoryMock.findByBasketIdAndUserName(any(), any())).thenReturn(basketList);

        // Act
        List<BasketDTO> basketRequestList = new ArrayList<>();
        BasketDTO basketRequest = new BasketDTO(1);
        basketRequestList.add(basketRequest);

        BasketEnquiryRequestDTO basketEnquiryRequest = new BasketEnquiryRequestDTO();
        basketEnquiryRequest.setUserName("test");
        basketEnquiryRequest.setBasketList(basketRequestList);

        BasketService basketService = new BasketService(null, basketRepositoryMock, null);
        List<BasketCreateEnquiryResponseDTO> responseList = basketService.findBasketListByBasketIdAndUserName(basketEnquiryRequest);

        // Assert
        assertEquals(responseList.size(), 1);
        assertEquals(responseList.get(0).getBasketId(), 1);
    }
}