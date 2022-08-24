package com.spharosacademy.project.SSGBack.user.exception;

public class MemberIdNotFound extends IllegalArgumentException{

    private final static String MESSAGE = "등록된 회원번호가 없습니다.";

    public MemberIdNotFound(){
        super(MESSAGE);
    }
}
