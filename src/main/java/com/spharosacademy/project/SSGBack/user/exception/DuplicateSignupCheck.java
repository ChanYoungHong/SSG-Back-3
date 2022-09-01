package com.spharosacademy.project.SSGBack.user.exception;

public class DuplicateSignupCheck extends IllegalArgumentException{

    private static final String MESSAGE = "이미 등록된 회원가입니디.";

    public DuplicateSignupCheck() {
        super(MESSAGE);
    }
}
