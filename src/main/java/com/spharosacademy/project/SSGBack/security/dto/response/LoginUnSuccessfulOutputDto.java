package com.spharosacademy.project.SSGBack.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUnSuccessfulOutputDto {

    private String isSuccess;
    private String message;

}
