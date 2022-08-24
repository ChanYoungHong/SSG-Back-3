package com.spharosacademy.project.SSGBack.user.dto.response;

import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {

    private Long id;
    private String userId;
    private String userPwd;
    private String userName;
    private String userAddress;
    private String userEmail;
    private String userPhone;
    private String memberType;
    private UserRole role;
    private String gender;
    private LocalDateTime userBirthDate, createdAt, updatedAt;
    private Boolean userDropCheck;

    List<UserEditInputDto> userEditInputDtoList;
}
