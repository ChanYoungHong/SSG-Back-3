package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserAddInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.List;
import org.springframework.context.annotation.Primary;


@Primary
public interface UserService {

    void registerUser(UserAddInputDto userAddInputDto);

    List<User> findAllByUserId(Long userId);

    void modifyUserInfo(Long memberId,UserOutputDto userOutputDto);

    User removeUserInfo(Long memberId, UserOutputDto userOutputDto);



}
