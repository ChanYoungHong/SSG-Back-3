package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.security.dto.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.security.exception.LoginFailException;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
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

    @PostMapping("/login")
    public void loginUser(){

    }

}
