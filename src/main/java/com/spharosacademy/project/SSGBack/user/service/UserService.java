package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.Optional;


public interface UserService {
    Optional<User> findByUserId(Long id);

    void modifyUserInfo(Long userId, UserInputDto userInputDto);

    User removeUserInfo(Long memberId, UserOutputDto userOutputDto);

}
