package com.spharosacademy.project.SSGBack.user.service.Impl;

import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.MemberIdNotfound;
import com.spharosacademy.project.SSGBack.user.exception.UserIdNotFound;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User registerUser(UserInputDto userInputDto) {


        if(userRepository.existsByUserId(userInputDto.getUserId())){

        }

        return userRepository.save(
            User.builder()
//                .memberId(userInputDto.getMemberId())
                .userId(userInputDto.getUserId())
                .userPwd(passwordEncoder.encode(userInputDto.getUserPwd()))
                .userAddress(userInputDto.getUserAddress())
                .userName(userInputDto.getUserName())
                .userEmail(userInputDto.getUserEmail())
                .userPhone(userInputDto.getUserPhoneNumber())
                .userBirthDate(userInputDto.getUserBirthDate())
                .gender(userInputDto.getGender())
                .memberType(userInputDto.getMemberType())
                .build()
        );
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User modifyUserInfo(UserOutputDto userOutputDto) {

        Optional<User> result = userRepository.findById(userOutputDto.getMemberId());

        if (result.isPresent()) {
            return userRepository.save(
                User.builder()
                    .memberId(userOutputDto.getMemberId())
                    .userId(userOutputDto.getUserId())
                    .userPwd(userOutputDto.getUserPwd())
                    .userAddress(userOutputDto.getUserAddress())
                    .userName(userOutputDto.getUserName())
                    .userEmail(userOutputDto.getUserEmail())
                    .userBirthDate(userOutputDto.getUserBirthDate())
                    .gender(userOutputDto.getGender())
                    .memberType(userOutputDto.getMemberType())
                    .build()
            );
        }
        return null;
    }

    @Override
    public User removeUserInfo(Long memberId, UserOutputDto userOutputDto) {

        Optional<User> check =
            Optional.ofNullable(userRepository.findById(userOutputDto.getMemberId()).orElseThrow(
                MemberIdNotfound::new));

        if (check.isPresent()) {
            if (userOutputDto.getUserDropCheck().equals(true)) {
                userRepository.deleteById(userOutputDto.getMemberId());
            }
        }

        return null;
    }


//    @Override
//    public User dtoToEntity(UserInputDto userInputDto) {
//        return UserService.super.dtoToEntity(userInputDto);
//    }

}
