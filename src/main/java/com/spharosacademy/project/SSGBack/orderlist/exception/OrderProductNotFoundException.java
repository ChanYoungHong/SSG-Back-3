package com.spharosacademy.project.SSGBack.orderlist.exception;

public class OrderProductNotFoundException extends RuntimeException {
    public static final String MESSAGE = "주문상품을 찾을 수 없습니다.";

    public OrderProductNotFoundException(){
        super(MESSAGE);
    }

}
