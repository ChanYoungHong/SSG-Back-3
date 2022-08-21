package com.spharosacademy.project.SSGBack.user.dto.response;

import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
import java.time.LocalDateTime;
import java.util.List;
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
    private String userId;
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

    List<UserEditInputDto> userEditInputDtoList;
}
