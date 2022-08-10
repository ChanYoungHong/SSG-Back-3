package com.spharosacademy.project.SSGBack.review.exception;

public class ReviewNotFoundException extends RuntimeException {


    public static final String MESSAGE = "제품이 존재하지 않습니다";

    public ReviewNotFoundException() {super(MESSAGE);
    }
}

// 모르겠는데?