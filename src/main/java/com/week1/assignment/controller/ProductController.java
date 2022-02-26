package com.week1.assignment.controller;

import com.week1.assignment.model.ProductDetailResponseDTO;
import com.week1.assignment.model.ProductInfoResponseDTO;
import com.week1.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductInfoResponseDTO> getProductInfo(@RequestParam("productName") Optional<String> productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/{id}")
    public ProductDetailResponseDTO getProductDetail(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }
}
