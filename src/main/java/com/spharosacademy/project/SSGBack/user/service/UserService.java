package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserChangePwdInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserRemoveDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserGetOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;


public interface UserService {

    boolean duplicateUserId(String userId);

    UserGetOutputDto findByUserId(String userId);

    void modifyUserInfo(Long id, UserInputDto userInputDto);

    User removeUserInfo(Long id, UserRemoveDto userRemoveDto);

    boolean changePassword(String userId, UserChangePwdInputDto userChangePwdInputDto);

    boolean verifyPassword(String userId, UserChangePwdInputDto userChangePwdInputDto);

}
