package com.week1.assignment.controller;

import com.week1.assignment.model.*;
import com.week1.assignment.service.BasketService;
import com.week1.assignment.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    BasketService basketService;

    @Test
    @DisplayName("create basket ด้วย productId = 1, productPrice = 1.0" +
            "quantity = 2.0, size = 3.0 แล้วจะต้องได้ " +
            "basket ที่มี quantity = 2.0, size = 3.0, productId = 1, total = 2.0")
    void caseCreateBasket() {
        // Arrange
        ProductSelectedDetailDTO productSelectedDetail = new ProductSelectedDetailDTO();
        productSelectedDetail.setProductId(1);
        productSelectedDetail.setPrice(1.0);

        List<BasketItemDTO> basketItems = new ArrayList<>();
        BasketItemDTO basketItem = new BasketItemDTO();
        basketItem.setQuantity(2.0);
        basketItem.setSize(3.0);
        basketItem.setProductDetail(productSelectedDetail);
        basketItems.add(basketItem);

        BasketCreateEnquiryResponseDTO basketCreateEnquiryResponse = new BasketCreateEnquiryResponseDTO();
        basketCreateEnquiryResponse.setBasketId(1);
        basketCreateEnquiryResponse.setBasketItems(basketItems);
        basketCreateEnquiryResponse.setTotal(2.0);

        when(basketService.createBasket(any())).thenReturn(basketCreateEnquiryResponse);
        // Act
        BasketCreateRequestDTO basketCreateRequest = new BasketCreateRequestDTO();
        BasketCreateEnquiryResponseDTO result = testRestTemplate.postForObject("/api/basket/create", basketCreateRequest, BasketCreateEnquiryResponseDTO.class);
        // Assert
        assertEquals(result.getBasketItems().get(0).getQuantity(),2.0);
        assertEquals(result.getBasketItems().get(0).getSize(),3.0);
        assertEquals(result.getTotal(),2.0);
        assertEquals(result.getBasketItems().get(0).getProductDetail().getProductId(),1);
    }

    @Test
    @DisplayName("get basket ด้วย basketId = 1" +
            "แล้วจะต้องได้ " +
            "basket ที่มี basketId = 1")
    void getBasketData() {
        // Arrange
        List<BasketCreateEnquiryResponseDTO> basketCreateEnquiryResponseList = new ArrayList<>();
        BasketCreateEnquiryResponseDTO basketCreateEnquiryResponse = new BasketCreateEnquiryResponseDTO();
        basketCreateEnquiryResponse.setBasketId(1);
        basketCreateEnquiryResponseList.add(basketCreateEnquiryResponse);

        when(basketService.findBasketListByBasketIdAndUserName(any())).thenReturn(basketCreateEnquiryResponseList);
        // Act
        BasketEnquiryRequestDTO basketEnquiryRequest = new BasketEnquiryRequestDTO();
        BasketCreateEnquiryResponseDTO[] result = testRestTemplate.postForObject("/api/basket/enquiry", basketEnquiryRequest, BasketCreateEnquiryResponseDTO[].class);
        // Assert
        assertEquals(result.length,1);
        assertEquals(Arrays.stream(result).findFirst().get().getBasketId(),1);
    }
}