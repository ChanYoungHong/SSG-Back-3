package com.spharosacademy.project.SSGBack.user.controller;

import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원정보 조회, 토큰으로 회원조회, memberId 지움
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public User findByUserId() {
        String token = jwtTokenProvider.te();

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return userService.findByUserId(userId);
    }

    // 회원정보 수정, 토큰으로 수정 memberId 지움
    @PutMapping("/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyUserInfo(HttpServletRequest request,
                               @RequestBody UserOutputDto userOutputDto) {
        String token = jwtTokenProvider.resolveToken(request);
        userService.modifyUserInfo(Long.valueOf(jwtTokenProvider.getUserPk(token)), userOutputDto);
    }

    // 회원 탈퇴, 삭제 + 토큰으로 삭제하기 memberId 지움
    @DeleteMapping("/remove")
    public User removeUserInfo(HttpServletRequest request,
                               @RequestBody UserOutputDto userOutputDto) {
        String token = jwtTokenProvider.resolveToken(request);

        return userService.removeUserInfo(Long.valueOf(jwtTokenProvider.getUserPk(token)), userOutputDto);
    }
}
