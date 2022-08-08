package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        UserInputDto userInputDto = UserInputDto.builder()
            .userId(123)
            .memberId(1L)
            .userName("홍찬영")
            .build();

        System.out.println(userService.addUser(userInputDto));
    }


}