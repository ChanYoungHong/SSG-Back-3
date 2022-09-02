package com.spharosacademy.project.SSGBack.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVerifyPasswordDto {
    private String userPwd;
}
