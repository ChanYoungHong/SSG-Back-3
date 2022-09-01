package com.spharosacademy.project.SSGBack.security.exception;

public class ExpiredTokenAcessException extends RuntimeException {

    private final static String MESSAGE = "토큰이 만료되었습니다. 재발급 해주세요.";

    public ExpiredTokenAcessException() {
        super(MESSAGE);
    }

}
