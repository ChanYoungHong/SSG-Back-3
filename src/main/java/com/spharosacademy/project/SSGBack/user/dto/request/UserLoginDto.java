package com.spharosacademy.project.SSGBack.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {

    private String userId;
    private String userPwd;

}
