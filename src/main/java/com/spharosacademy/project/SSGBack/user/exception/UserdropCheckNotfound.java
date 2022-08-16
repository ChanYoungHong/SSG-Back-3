package com.spharosacademy.project.SSGBack.user.exception;

public class UserdropCheckNotfound extends RuntimeException {
    public static final String MESSAGE = "회원 탈퇴 여부를 체크하지 않으셨습니다."; // 탈퇴 할 때의 상황

    public UserdropCheckNotfound() {
        super(MESSAGE);
    }

}
