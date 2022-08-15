package com.spharosacademy.project.SSGBack.user.controller;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입 등록
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserInputDto userInputDto) {
        return userService.registerUser(userInputDto);
    }

    // 회원가입 조회
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }


    @PutMapping("/modify")
    @ResponseStatus(HttpStatus.OK)
    public User modifyUser(@RequestBody UserOutputDto userOutputDto){
        return userService.modifyUserInfo(userOutputDto);
    }

    @DeleteMapping("/remove/{memberId}")
    public User modifyUser(@PathVariable Long memberId,
                           @RequestBody UserOutputDto userOutputDto){
        return userService.removeUserInfo(memberId, userOutputDto);
    }

}
