package com.week1.assignment.service;

import com.week1.assignment.entity.Product;
import com.week1.assignment.exception.ProductNotFoundException;
import com.week1.assignment.model.ProductDetailResponseDTO;
import com.week1.assignment.model.ProductInfoResponseDTO;
import com.week1.assignment.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private static ProductRepository productRepositoryMock;

    private static ProductService productService;

    @BeforeAll
    public static void setup() {
        productRepositoryMock = mock(ProductRepository.class);
        productService = new ProductService(productRepositoryMock);
    }

    @Test
    @DisplayName("ส่ง name = test แล้วจะต้องได้ product ที่มี name = test")
    void caseProductFindByNameTest() {

        // Arrange
        // Product Entity
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        product.setId(1);
        product.setName("test");
        productList.add(product);

        // Product Response
        when(productRepositoryMock.findByNameContainingIgnoreCase("test"))
                .thenReturn(productList);

        // Act
        List<ProductInfoResponseDTO> result = productService.findByName(Optional.of("test"));

        // Assert
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getName(),"test");
    }

    @Test
    @DisplayName("ส่ง name = failed แล้วจะต้องได้ข้อความ Product name: failed not found")
    void caseFindByNameWithFailure() {
        // Arrange
        when(productRepositoryMock.findByNameContainingIgnoreCase("failed")).thenReturn(Collections.emptyList());
        // Act
        ProductService productService = new ProductService(productRepositoryMock);
        ProductNotFoundException thrown = Assertions.assertThrows(ProductNotFoundException.class,
                () -> productService.findByName(Optional.of("failed")));
        // Assert
        Assertions.assertEquals("Product name: failed not found", thrown.getMessage());
    }

    @Test
    @DisplayName("ส่ง id = 0 แล้วจะต้องได้ product ที่มี id = 0, name = test")
    void caseFindById0() {
        // Arrange
        Product product = new Product();
        product.setId(0);
        product.setName("test");
        when(productRepositoryMock.findById(0)).thenReturn(Optional.of(product));
        // Act
        ProductService productService = new ProductService(productRepositoryMock);
        ProductDetailResponseDTO result = productService.findById(0);
        // Assert
        assertEquals(result.getId(),0);
        assertEquals(result.getName(),"test");
    }

    @Test
    @DisplayName("ส่ง id = 10 แล้วจะต้องได้ข้อความ Product id: 10 not found")
    void caseFindByIdWithFailure() {
        // Arrange
        when(productRepositoryMock.findById(10)).thenReturn(Optional.empty());
        // Act
        ProductService productService = new ProductService(productRepositoryMock);
        ProductNotFoundException thrown = Assertions.assertThrows(ProductNotFoundException.class,
                () -> productService.findById(10));
        // Assert
        Assertions.assertEquals("Product id: 10 not found", thrown.getMessage());
    }
}