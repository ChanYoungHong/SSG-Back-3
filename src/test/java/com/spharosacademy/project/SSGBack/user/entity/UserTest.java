package com.spharosacademy.project.SSGBack.user.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            User user = User.builder()
                .userId(String.valueOf(i))
                .userPwd(passwordEncoder.encode("1111"))
                .userName("user"+i+"님")
                .userEmail("user" + i + "@naver.com")
                .userAddress("집 주소 성산구 사파동" + i + "호")
                .userPhone("@@@@@@@@@")
                .memberType("")
                .gender("")
                .build();

            user.addUserRole(UserRole.ROLE_USER);

            if( i > 80) {
                user.addUserRole(UserRole.ROLE_MANAGER);
            }

            if( i > 90) {
                user.addUserRole(UserRole.ROLE_ADMIN);
            }

            userRepository.save(user);
        });
    }

    @Test
    public void testRead() {

        Optional<User> result = userRepository.findByUserEmail("user95@naver.com", false);

        User user = result.get();
        System.out.println(user);
    }

}