package com.spharosacademy.project.SSGBack.user.dto.response;

import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserGetOutputDto {

    private String userId;
    private String name;
    private String userAddress;
    private String userEmail;
    private String userPhone;
    private String memberType;
    private UserRole role;

}
