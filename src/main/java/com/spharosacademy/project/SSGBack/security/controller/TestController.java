package com.spharosacademy.project.SSGBack.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {

    @PostMapping("/test")
    public String test() {
        return "통과";
    }
}
