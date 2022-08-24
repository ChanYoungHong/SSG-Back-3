package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.exception.LoginFailException;
import com.spharosacademy.project.SSGBack.user.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@CrossOrigin
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityLoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginSuccessOutputDto loginUser(
        @RequestBody LoginSuccessOutputDto loginSuccessOutputDto) {

        User result = userRepository.findByUserId(loginSuccessOutputDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));

        log.info(loginSuccessOutputDto);

        if (passwordEncoder.matches(loginSuccessOutputDto.getUserPwd(), result.getUserPwd())) {
            return LoginSuccessOutputDto.builder()
                .message("토큰이 생성 되었습니다.")
                .result(String.valueOf(
                    jwtTokenProvider.createToken(result.getId(),
                        String.valueOf(result.getRole()))))
                .isSuccess("성공")
                .userEmail(loginSuccessOutputDto.getUserEmail())
                .build();
        } else {
            new LoginFailException();
        }
        return loginSuccessOutputDto;
    }



}