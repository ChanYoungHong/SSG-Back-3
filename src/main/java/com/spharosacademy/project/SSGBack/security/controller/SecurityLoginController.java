package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public LoginSuccessOutputDto loginUser(@RequestBody UserLoginDto userLoginDto){

        User result = userRepository.findByUserEmail(userLoginDto.getUserEmail(), false)
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));

        if(passwordEncoder.matches(result.getUserPwd(), userLoginDto.getUserPwd())) {
            return LoginSuccessOutputDto.builder()
                .message("성공")
                .result(String.valueOf(
                    jwtTokenProvider.createToken(result.getUsername(), result.getRoles())))
                .build();
        } else {
            return null;
        }
    }
}
