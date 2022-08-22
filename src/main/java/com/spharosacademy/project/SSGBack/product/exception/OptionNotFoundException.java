package com.spharosacademy.project.SSGBack.product.exception;

public class OptionNotFoundException extends RuntimeException {
    private static final String MESSAGE = "선택하신 옵션이 존재하지 않습니다";

    public OptionNotFoundException() {
        super(MESSAGE);
    }
}
