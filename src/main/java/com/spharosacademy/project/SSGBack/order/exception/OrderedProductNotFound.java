package com.spharosacademy.project.SSGBack.order.exception;

public class OrderedProductNotFound extends RuntimeException{
    private static final String MESSAGE = "주문하신 상품을 찾을 수 없습니다.";

    public OrderedProductNotFound() {
        super(MESSAGE);
    }
}
