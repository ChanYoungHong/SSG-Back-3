package com.spharosacademy.project.SSGBack.product.exception;

public class UserNotFoundException extends RuntimeException {
    public static final String MESSAGE = "사용자가 존재하지 않습니다";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
