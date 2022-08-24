package com.spharosacademy.project.SSGBack.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditInputDto {

    private String userPhoneNumber;
    private String userEmail;
}
