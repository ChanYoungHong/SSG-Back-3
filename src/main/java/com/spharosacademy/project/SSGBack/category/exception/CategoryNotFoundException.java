package com.spharosacademy.project.SSGBack.category.exception;

public class CategoryNotFoundException extends RuntimeException {

    public static final String Message="카테고리가 존재하지 않습니다";

    public CategoryNotFoundException() {
        super(Message);
    }
}
