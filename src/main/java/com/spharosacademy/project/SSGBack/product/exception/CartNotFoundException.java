package com.spharosacademy.project.SSGBack.product.exception;

public class CartNotFoundException extends RuntimeException {
    public static final String MESSAGE = "선택하신 장바구니 상품이 존재하지 않습니다";

    public CartNotFoundException() {
        super(MESSAGE);
    }
}
