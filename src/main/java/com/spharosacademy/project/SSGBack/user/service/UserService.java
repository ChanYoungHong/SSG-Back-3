package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserChangePwdInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.Optional;


public interface UserService {

    boolean duplicateUserId(String userId);

    Optional<User> findByUserId(Long id);

    void modifyUserInfo(Long id, UserInputDto userInputDto);

    User removeUserInfo(Long id, UserOutputDto userOutputDto);

    Optional<User> changePassword(String userId, UserChangePwdInputDto userChangePwdInputDto);

}
