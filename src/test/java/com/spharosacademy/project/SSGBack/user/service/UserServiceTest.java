package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserAddInputDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        UserAddInputDto userAddInputDto = UserAddInputDto.builder()
            .userId(String.valueOf(123))
            .userPwd("1234")
            .userAddress("광안리 수영구 138-1")
            .userEmail("cksdu9883@naver.com")
            .userName("홍찬영")
            .userPhoneNumber("01036699883")
            .gender("남")
            .role("사용자")
            .memberType("프렌드")
            .build();


//        System.out.println(userService.registerUser(userAddInputDto));
    }


    @Test
    void registerUser() {
    }
}