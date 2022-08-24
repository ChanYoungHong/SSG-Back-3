package com.spharosacademy.project.SSGBack.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessOutputDto {

    private String isSuccess;
    private String message;
    private String result;
    private String userEmail;
    private String userPwd;
    private String userId;

}
