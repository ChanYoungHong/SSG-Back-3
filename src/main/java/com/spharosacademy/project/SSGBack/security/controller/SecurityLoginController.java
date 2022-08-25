package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.exception.LoginFailException;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.DuplicatedUserIdCheck;
import com.spharosacademy.project.SSGBack.user.exception.UserIdNotFound;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody UserLoginDto userLoginDto) {

        User result = userRepository.findByUserId(userLoginDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email입니다."));
        log.info("+++++++++++++++++++++++++++++++++++");
        log.info(userLoginDto.getUserPwd());
        log.info(result.getUserPwd());
        log.info("+++++++++++++++++++++++++++++++++++");
        if (passwordEncoder.matches(userLoginDto.getUserPwd(), result.getUserPwd())) {
            return LoginSuccessOutputDto.builder()
                .message("토큰이 생성 되었습니다.")
                .result(String.valueOf(
                    jwtTokenProvider.createToken(result.getId(),
                            String.valueOf(result.getRole()))))
                .isSuccess("성공")
                .build();
        } else {
            throw new LoginFailException();
        }
    }
    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<String> handleLoginFailException(LoginFailException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
