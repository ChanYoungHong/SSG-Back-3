package com.spharosacademy.project.SSGBack.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {

    UNKNOWN_ERROR(1003, "토큰이 존재하지 않습니다."),
    EXPIRED_TOKEN(1005, "만료된 토큰입니다."),
    WRONG_TYPE_TOKEN(1004, "잘 못된 토큰타입입니다."),
    UNSUPPORTED_TOKEN(1006, "이 형식의 토큰을 지원하지 않습니다."),
    WRONG_TOKEN(1007, "잘 못된 토큰타입입니다."),

    PERMISSION_DENIED(1008, "권한 거부"),

    ACCESS_DENIED(1009, "접근 권한 에러");

    private Integer code;
    private String message;


}
