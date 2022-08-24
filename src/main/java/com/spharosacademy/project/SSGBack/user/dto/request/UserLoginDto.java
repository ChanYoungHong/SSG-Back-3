package com.spharosacademy.project.SSGBack.user.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {
    private String userEmail;
    private String userPwd;
}
