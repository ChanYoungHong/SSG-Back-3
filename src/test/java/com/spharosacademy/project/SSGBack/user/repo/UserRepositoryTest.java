package com.spharosacademy.project.SSGBack.user.repo;

import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testClass() {
        System.out.println(userRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

        });
    }

    @Test
    public void updateTest() {
        Optional<User> result = userRepository.findById(3L);

        if(result.isPresent()){

            User user = result.get();

            userRepository.save(user);
        }


    }

}