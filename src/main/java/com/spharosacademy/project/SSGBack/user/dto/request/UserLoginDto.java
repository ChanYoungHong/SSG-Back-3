package com.spharosacademy.project.SSGBack.user.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    private String userEmail;
    private String userPwd;
    private String userId;
}
