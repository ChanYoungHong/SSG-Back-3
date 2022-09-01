package com.spharosacademy.project.SSGBack.user.exception;

public class UserIdNotFound extends IllegalArgumentException {
    private final static String MESSAGE = "유저아이디를 찾을 수 없습니다.";

    public UserIdNotFound(){
        super(MESSAGE);
    }
}
