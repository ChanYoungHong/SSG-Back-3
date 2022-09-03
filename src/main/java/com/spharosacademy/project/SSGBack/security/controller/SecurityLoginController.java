package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.security.exception.LoginFailException;
import com.spharosacademy.project.SSGBack.user.dto.request.UserChangePwdInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.UserIdNotFound;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    private final UserService userService;

    @PostMapping("/login")
    public LoginSuccessOutputDto loginUser(
        @RequestBody UserLoginDto userLoginDto) {

        UserChangePwdInputDto userChangePwdInputDto = new UserChangePwdInputDto();

        User user = userRepository.findByUserId(userLoginDto.getUserId()).orElseThrow(
            UserIdNotFound::new);

        boolean userPwd = userService.verifyPassword(userLoginDto.getUserId(), userChangePwdInputDto);

//        if (passwordEncoder.matches(userLoginDto.getUserPwd(), user.getUserPwd())) {
//            return LoginSuccessOutputDto.builder()
//                .message("토큰이 생성 되었습니다.")
//                .result(String.valueOf(
//                    jwtTokenProvider.createToken(user.getUserId(),
//                        String.valueOf(user.getRole()))))
//                .isSuccess("성공")
//                .build();
//        } else {
//            throw new LoginFailException();
//        }
        return null;
    }

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<String> handleLoginFailException(LoginFailException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
