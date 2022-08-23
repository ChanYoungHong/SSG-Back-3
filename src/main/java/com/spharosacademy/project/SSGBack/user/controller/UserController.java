package com.spharosacademy.project.SSGBack.user.controller;

import com.spharosacademy.project.SSGBack.user.dto.request.UserAddInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입 등록
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserAddInputDto userAddInputDto) {
        userService.registerUser(userAddInputDto);
    }

    // 회원가입 조회
    @GetMapping("/get/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser(@PathVariable Long userId) {
        return userService.findAllByUserId(userId);
    }

    // 회원정보 변경과 수정
    @PutMapping("/modify/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyUserInfo(@PathVariable Long memberId,
                               @RequestBody UserOutputDto userOutputDto) {
        userService.modifyUserInfo(memberId, userOutputDto);
    }

    // 회원 탈퇴, 삭제
    @DeleteMapping("/remove/{memberId}")
    public User removeUserInfo(@PathVariable Long memberId,
                               @RequestBody UserOutputDto userOutputDto) {
        return userService.removeUserInfo(memberId, userOutputDto);
    }

}
