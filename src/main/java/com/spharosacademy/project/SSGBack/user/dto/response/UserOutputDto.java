package com.spharosacademy.project.SSGBack.user.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserOutputDto {

    private Long memberId;
    private Long userId;
    private String userPwd;
    private String userName;
    private String userAddress;
    private String userEmail;
    private String userPhone;
    private String memberType;
    private String role;
    private String gender;
    private LocalDateTime userBirthDate, createdAt, updatedAt;
    private Boolean userDropCheck;
}
