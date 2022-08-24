package com.spharosacademy.project.SSGBack.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInputDto {


//    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

//    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPwd;

//    @NotBlank(message = "주소를 입력해주세요.")
    private String userAddress;

//    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;
//
//    @NotBlank(message = "이메일을 입력해주세요.")
    private String userEmail;

//    @NotBlank(message = "핸드폰 번호를 입력해주세요.")
    private String userPhoneNumber;

//    private LocalDate userBirthDate;

    private Boolean userDropCheck;


    private String memberType;

//    @Length(max = 6)
////    private UserRole role;

    List<UserEditInputDto> userEditInputDtoList;

}
