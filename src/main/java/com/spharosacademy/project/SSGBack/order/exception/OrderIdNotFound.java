package com.spharosacademy.project.SSGBack.order.exception;

public class OrderIdNotFound extends RuntimeException {

    private static final String MESSAGE = "주문번호를 찾을 수 없습니다.";

    public OrderIdNotFound() {
        super(MESSAGE);
    }

}
