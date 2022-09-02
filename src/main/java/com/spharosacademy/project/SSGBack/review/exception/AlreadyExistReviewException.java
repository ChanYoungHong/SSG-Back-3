package com.spharosacademy.project.SSGBack.review.exception;

public class AlreadyExistReviewException extends RuntimeException{
    public static final String MESSAGE = "이미 주문하신 상품에 대한 리뷰를 작성하셨습니다";

    public AlreadyExistReviewException() {
        super(MESSAGE);
    }
}
