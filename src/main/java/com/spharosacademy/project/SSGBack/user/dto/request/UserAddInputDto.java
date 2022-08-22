package com.spharosacademy.project.SSGBack.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddInputDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPwd;

    @NotBlank(message = "주소를 입력해주세요.")
    private String userAddress;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]+", message = "이름을 정확히 입력하여주십시오.")
    private String userName;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String userEmail;

    @NotBlank(message = "핸드폰 번호를 입력해주세요.")
    private String userPhoneNumber;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private LocalDateTime userBirthDate;

    private String memberType;

    @NotBlank
    @Length(max = 1)
    @Pattern(
        regexp = "^[남|녀]$",
        message = "성별은 남, 녀만 입력이 가능합니다."
    )
    private String gender;

    @Length(max = 6)
    private String role;

    @NotBlank(message = "탈퇴 여부를 입력해주세요")
    private Boolean userDropCheck;


}
