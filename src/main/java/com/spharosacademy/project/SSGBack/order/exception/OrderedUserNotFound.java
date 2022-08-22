package com.spharosacademy.project.SSGBack.order.exception;

public class OrderedUserNotFound extends RuntimeException {

    private static final String MESSAGE = "주문한 사용자를 찾을 수 없습니다.";

    public OrderedUserNotFound(){
        super(MESSAGE);
    }
}
