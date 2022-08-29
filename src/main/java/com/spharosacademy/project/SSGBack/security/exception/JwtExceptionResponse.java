package com.spharosacademy.project.SSGBack.security.exception;

import org.springframework.http.HttpStatus;

public class JwtExceptionResponse {

    HttpStatus httpStatus;
    String message = "토큰이 없습니다 !!!!";

    public int convertToJson() {
        return 0;
    }

    public JwtExceptionResponse(String message, HttpStatus httpStatus){

    }
}
