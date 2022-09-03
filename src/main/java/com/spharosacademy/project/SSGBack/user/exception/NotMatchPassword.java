package com.spharosacademy.project.SSGBack.user.exception;

public class NotMatchPassword extends IllegalArgumentException {

    private static final String MESSAGE = "비밀번호가 맞지 않습니다.";

    public NotMatchPassword() {
        super(MESSAGE);
    }
}
