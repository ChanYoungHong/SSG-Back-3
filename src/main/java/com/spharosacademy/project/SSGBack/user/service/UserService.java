package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.order.dto.output.OrderOutputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;

import java.util.List;

public interface UserService {

    User findByUserId(Long userId);

    void modifyUserInfo(Long memberId, UserOutputDto userOutputDto);

    User removeUserInfo(Long memberId, UserOutputDto userOutputDto);
}
