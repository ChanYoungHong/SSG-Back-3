package com.spharosacademy.project.SSGBack.user.exception;

public class DuplicatedUserIdCheck extends IllegalArgumentException {

    private final static String MESSAGE = "사용 중인 아이디입니다";

    public DuplicatedUserIdCheck() {
        super(MESSAGE);
    }
}
