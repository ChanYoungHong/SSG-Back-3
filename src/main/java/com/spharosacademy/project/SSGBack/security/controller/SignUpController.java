package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.service.SignUpService;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    // 사용자 회원 로그인
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserInputDto userInputDto) {
        signUpService.registerUser(userInputDto);
        return "회원가입 되셨습니다.";
    }

}