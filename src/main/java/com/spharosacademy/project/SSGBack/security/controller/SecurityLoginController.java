package com.spharosacademy.project.SSGBack.security.controller;

import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Configuration
@Log4j2
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityLoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public String loginUser(@RequestBody Map<String, String> user){
        User result = userRepository.findByUserEmail(user.get("userEmail"), false)
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));
        return jwtTokenProvider.createToken(result.getUsername(), result.getRoles());
    }



}
