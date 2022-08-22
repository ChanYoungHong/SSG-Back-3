package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityLoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginSuccessOutputDto loginUser(
        @RequestBody LoginSuccessOutputDto loginSuccessOutputDto) {

        UserLoginDto userLoginDto = new UserLoginDto();

        User result = userRepository.findByUserEmail(loginSuccessOutputDto.getUserEmail(), false)
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));

        log.info("if 안으로 들어오니??");
        log.info(result.getUserPwd());
        log.info(loginSuccessOutputDto.getUserPwd());

        if (!passwordEncoder.matches(result.getUserPwd(), userLoginDto.getUserPwd())) {
            return LoginSuccessOutputDto.builder()
                .message("비밀번호 인증 실패입니다.")
                .build();
//            log.info("성공인가??");
//            LoginSuccessOutputDto.builder()
//                .message("토큰이 생성 되었습니다.")
//                .result(String.valueOf(
//                    jwtTokenProvider.createToken(result.getUsername(), result.getRoles())))
//                .build();
        } else {

            log.info("성공인가?/");
            return LoginSuccessOutputDto.builder()
                .message("토큰이 생성 되었습니다.")
                .result(String.valueOf(
                    jwtTokenProvider.createToken(result.getUsername(), result.getRoles())))
                .isSuccess("성공")
                .userEmail(loginSuccessOutputDto.getUserEmail())
                .build();
        }
    }
}
