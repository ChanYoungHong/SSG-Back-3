package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.List;

public interface UserService {

    User registerUser(UserInputDto userInputDto);

    List<User> getAllUser();

    User modifyUserInfo(UserOutputDto userOutputDto);

    User removeUserInfo(Long memberId, UserOutputDto userOutputDto);



    default User dtoToEntity(UserInputDto userInputDto){
        User entity = User.builder()
            .userId(String.valueOf(userInputDto.getUserId()))
//            .memberId(userInputDto.getMemberId())
            .userName(userInputDto.getUserName())
            .build();
        return entity;
    }

}
