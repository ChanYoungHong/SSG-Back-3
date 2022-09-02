package com.spharosacademy.project.SSGBack.review.exception;

public class NotOrderProductException extends RuntimeException {

    public static final String MESSAGE = "주문하신 상품이 아니므로 리뷰를 작성하실 수 없습니다";

    public NotOrderProductException() {
        super(MESSAGE);
    }
}
