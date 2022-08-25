package com.spharosacademy.project.SSGBack.security.service.Impl;

import com.spharosacademy.project.SSGBack.security.service.SignUpService;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SingUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserInputDto userInputDto) {

       return userRepository.save(
                User.builder()
                        .userId(userInputDto.getUserId())
                        .userPwd(passwordEncoder.encode(userInputDto.getUserPwd()))
                        .userAddress(userInputDto.getUserAddress())
                        .userName(userInputDto.getUserName())
                        .userEmail(userInputDto.getUserEmail())
                        .role(UserRole.ROLE_USER)
                        .userPhone(userInputDto.getUserPhoneNumber())
                        .userDropCheck(userInputDto.getUserDropCheck())
                        .memberType(userInputDto.getMemberType())
                        .build()
        );
    }
}
