package com.week1.assignment.repository;

import com.week1.assignment.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("ส่ง name = test แล้วจะต้องได้รับ product ที่มี name = test")
    void caseFindByNameTest() {
        // Arrange
        Product product = new Product();
        product.setName("test");
        productRepository.save(product);
        // Act
        List<Product> result = productRepository.findByNameContainingIgnoreCase("test");
        // Assert
        assertEquals(result.size(), 1);
    }
}