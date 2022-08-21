package com.spharosacademy.project.SSGBack.order.exception;

public class OutOfStockException extends RuntimeException{
    public static final String MESSAGE = "주문하려고 하시는 상품의 현재 재고가 부족합니다";

    public OutOfStockException(){
        super(MESSAGE);
    }
}
