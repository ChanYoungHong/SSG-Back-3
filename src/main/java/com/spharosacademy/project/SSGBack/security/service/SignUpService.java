package com.spharosacademy.project.SSGBack.security.service;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;


public interface SignUpService {
    User registerUser(UserInputDto userInputDto);
}
