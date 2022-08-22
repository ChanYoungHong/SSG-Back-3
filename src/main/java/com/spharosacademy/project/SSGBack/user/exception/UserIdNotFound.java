package com.spharosacademy.project.SSGBack.user.exception;

public class UserIdNotFound extends RuntimeException {
    private final String MESSAGE = "유저아이디를 찾을 수 없습니다.";

    public UserIdNotFound(String MESSAGE){
        super(MESSAGE);
    }
}
