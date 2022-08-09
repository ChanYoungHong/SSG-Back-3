package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.List;

public interface UserService {

    User addUser(UserInputDto userInputDto);

    List<User> getAllUser();

    default User dtoToEntity(UserInputDto userInputDto){
        User entity = User.builder()
            .userId(userInputDto.getUserId())
            .memberId(userInputDto.getMemberId())
            .userName(userInputDto.getUserName())
            .build();
        return entity;
    }






}
