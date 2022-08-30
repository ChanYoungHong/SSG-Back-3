package com.spharosacademy.project.SSGBack.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {

    UNKNOWN_ERROR(1003, "토큰이 존재하지 않습니다."),
    EXPIRED_TOKEN(1005, "만료된 토큰입니다.");

    private int code;
    private String message;


}
