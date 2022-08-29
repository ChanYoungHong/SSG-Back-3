package com.spharosacademy.project.SSGBack.security.exception;

public class CAuthenticationEntryException extends RuntimeException {

    private final static String message = "토큰이 만료되었습니다.";

    public CAuthenticationEntryException() {
        super();
    }

    public CAuthenticationEntryException(String message){
        super(message);
    }

    public CAuthenticationEntryException(String message, Throwable cause) {
        super(message, cause);
    }

}
