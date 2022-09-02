package com.spharosacademy.project.SSGBack.user.controller;

import com.spharosacademy.project.SSGBack.user.dto.request.UserChangePwdInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.DuplicatedUserIdCheck;
import com.spharosacademy.project.SSGBack.user.exception.NotVerifyPassword;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //비밀번호 검증
    @PostMapping("/user/verify/password/{userId}")
    public ResponseEntity<?> verifyPassword(@PathVariable String userId,
                               @RequestBody UserChangePwdInputDto userChangePwdInputDto) {


        if(userService.verifyPassword(userId, userChangePwdInputDto) == true){
            return ResponseEntity.ok("비밀번호 검증이 완료되었습니다.");
        } else {
            throw new NotVerifyPassword();
        }
    }

    // 비밀번호 변경
    @PutMapping("/user/change/password")
    public Optional<User> changeNewPassWord(HttpServletRequest request,
                                            @RequestBody UserChangePwdInputDto userChangePwdInputDto) {

        String token = jwtTokenProvider.resolveToken(request); // User pk를 받아옴
        return userService.changePassword(jwtTokenProvider.getUserId(token), userChangePwdInputDto);
    }

    // 회원아이디 중복검사
    @GetMapping("/users/duplicate/{userId}")
    public ResponseEntity<?> duplicateUserId(@PathVariable String userId) {

        if (userService.duplicateUserId(userId) == true) {
            throw new DuplicatedUserIdCheck();
        } else {
            return ResponseEntity.ok("사용 가능한 아이디 입니다.");
        }

    }

    // 회원정보 조회, 토큰으로 회원조회, memberId 지움
    @GetMapping("/user/get")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findByUserId(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        return userService.findByUserId(jwtTokenProvider.getUserId(token));
    }


    // 회원정보 수정, 토큰으로 수정 memberId 지움
    @PutMapping("/user/modify")
    @ResponseStatus(HttpStatus.OK)
    public void modifyUserInfo(HttpServletRequest request,
                               @RequestBody UserInputDto userInputDto) {

        String token = jwtTokenProvider.resolveToken(request);

        userService.modifyUserInfo(jwtTokenProvider.getUserId(token), userInputDto);
    }

    // 회원 탈퇴, 삭제 + 토큰으로 삭제하기 memberId 지움
    @DeleteMapping("/user/remove")
    public User removeUserInfo(HttpServletRequest request,
                               @RequestBody UserOutputDto userOutputDto) {
        String token = jwtTokenProvider.resolveToken(request);
        Long id = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return userService.removeUserInfo(id, userOutputDto);
    }

    @ExceptionHandler(DuplicatedUserIdCheck.class)
    public ResponseEntity<String> handleOutofStockException(DuplicatedUserIdCheck exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
