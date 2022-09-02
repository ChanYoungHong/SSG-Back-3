package com.spharosacademy.project.SSGBack.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEditInputDto {

    private String userPhoneNumber;
    private String userEmail;
    private String userAddress;
    private String userName;

}
