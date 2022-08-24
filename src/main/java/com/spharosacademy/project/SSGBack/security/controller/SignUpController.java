package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.service.SignUpService;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/singup")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserInputDto userInputDto) {
        signUpService.registerUser(userInputDto);
        return "회원가입 되셨습니다.";
    }
}
