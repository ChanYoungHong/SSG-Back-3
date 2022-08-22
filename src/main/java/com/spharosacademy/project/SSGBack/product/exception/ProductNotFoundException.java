package com.spharosacademy.project.SSGBack.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public static final String MESSAGE = "제품이 존재하지 않습니다";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}