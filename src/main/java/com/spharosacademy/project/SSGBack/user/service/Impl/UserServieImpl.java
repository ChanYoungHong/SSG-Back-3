package com.spharosacademy.project.SSGBack.user.service.Impl;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServieImpl(
        UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public User addUser(UserInputDto userInputDto) {

//        User entity = dtoToEntity(userInputDto);
//        log.info(String.valueOf(userInputDto));
//        System.out.println("===========================");
//        log.info(String.valueOf(entity));
//
//        return null;


        return userRepository.save(
            User.builder()
                .memberId(userInputDto.getMemberId())
                .userId(userInputDto.getUserId())
                .userPwd(userInputDto.getUserPwd())
                .userAddress(userInputDto.getUserAddress())
                .userName(userInputDto.getUserName())
                .userEmail(userInputDto.getUserEmail())
                .userPhone(userInputDto.getUserPhoneNumber())
                .userBirthDate(userInputDto.getUserBirthDate())
                .gender(userInputDto.getGender())
                .role(userInputDto.getRole())
                .memberType(userInputDto.getMemberType())
                .build()
        );
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User dtoToEntity(UserInputDto userInputDto) {
        return UserService.super.dtoToEntity(userInputDto);
    }
}
