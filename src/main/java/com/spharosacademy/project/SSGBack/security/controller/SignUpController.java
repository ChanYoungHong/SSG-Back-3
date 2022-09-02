package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.service.SignUpService;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.DuplicateSignupCheck;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;
    private final UserService userService;

    // 사용자 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUser(@RequestBody UserInputDto userInputDto) {

        User registerUser = signUpService.registerUser(userInputDto);
        return ResponseEntity.ok().body("회원가입 되었습니다.");
    }

    @GetMapping("/social/{token}")
    public String sendToken(@PathVariable String token){
        return token;
    }



}
