package com.spharosacademy.project.SSGBack.security.controller;


import com.spharosacademy.project.SSGBack.security.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exception")
public class ExceptionController {

//    @GetMapping("/entrypoint")
//    public CommonResult entrypointException() {
//        return new CustomAuthenticationEntryPoint();
//    }



}
