package com.week1.assignment.controller;

import com.week1.assignment.model.ProductDetailResponseDTO;
import com.week1.assignment.model.ProductInfoResponseDTO;
import com.week1.assignment.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("ส่ง name = test แล้วจะต้องได้ product ที่มี name = test")
    void caseGetProductByNameTest() {
        // Arrange
        ProductInfoResponseDTO product = new ProductInfoResponseDTO();
        List<ProductInfoResponseDTO> productList = new ArrayList<>();
        product.setId(1);
        product.setName("test");
        productList.add(product);
        when(productService.findByName(Optional.of("test"))).thenReturn(productList);
        // Act
        ProductInfoResponseDTO[] result = testRestTemplate.getForObject("/api/products/?productName=test", ProductInfoResponseDTO[].class);
        // Assert
        assertEquals(result.length,1);
        assertEquals(Arrays.stream(result).findFirst().get().getName(), "test");
    }

    @Test
    @DisplayName("ส่ง id = 0 แล้วจะต้องได้ product ที่มี id = 1, name = test")
    void caseGetProductById0() {
        // Arrange
        ProductDetailResponseDTO productDetailResponseDTO = new ProductDetailResponseDTO();
        productDetailResponseDTO.setId(1);
        productDetailResponseDTO.setName("test");
        when(productService.findById(0)).thenReturn(productDetailResponseDTO);
        // Act
        ProductDetailResponseDTO result = testRestTemplate.getForObject("/api/products/0", ProductDetailResponseDTO.class);
        // Assert
        assertEquals(result.getId(),1);
        assertEquals(result.getName(),"test");
    }
}