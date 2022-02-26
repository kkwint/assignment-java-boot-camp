package com.week1.assignment.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
        super(name);
    }
}
