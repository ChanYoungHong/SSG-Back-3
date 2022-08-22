package com.spharosacademy.project.SSGBack.security;

import static org.assertj.core.api.Assertions.assertThat;

import com.spharosacademy.project.SSGBack.user.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testEncode(){

        String password = "1234";

        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw : " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println("matchResult : " + matchResult);

    }

    @Test
    public void RealTestEncode() {
        LoginSuccessOutputDto loginSuccessOutputDto = new LoginSuccessOutputDto();
        User user = new User();

        Optional<User>
            result = userRepository.findByUserEmail(user.getUserPwd(), false);

        if(passwordEncoder.matches(result.get().getUserPwd(), loginSuccessOutputDto.getUserPwd())) {
            assertThat(true);
        };

    }

}
