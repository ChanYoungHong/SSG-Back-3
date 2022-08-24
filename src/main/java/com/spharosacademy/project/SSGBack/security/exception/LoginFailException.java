package com.spharosacademy.project.SSGBack.security.exception;

public class LoginFailException extends IllegalArgumentException{

    private static final String MESSAGE = "로그인 실패하였습니다. 다시 로그인해주세요";

    public LoginFailException() {
        super(MESSAGE);
    }
}
