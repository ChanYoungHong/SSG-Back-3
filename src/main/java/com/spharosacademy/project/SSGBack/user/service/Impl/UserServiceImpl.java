package com.spharosacademy.project.SSGBack.user.service.Impl;

import com.spharosacademy.project.SSGBack.user.dto.request.UserChangePwdInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserRemoveDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserGetOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.MemberIdNotfound;
import com.spharosacademy.project.SSGBack.user.exception.UserIdNotFound;
import com.spharosacademy.project.SSGBack.user.exception.UserdropCheckNotfound;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean duplicateUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public UserGetOutputDto findByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        return UserGetOutputDto.builder()
                .userId(user.get().getUserId())
                .name(user.get().getName())
                .userAddress(user.get().getUserAddress())
                .userEmail(user.get().getUserEmail())
                .userPhone(user.get().getUserPhone())
                .memberType(user.get().getMemberType())
                .role(user.get().getRole())
                .build();
    }

    @Override
    public void modifyUserInfo(Long id, UserInputDto userInputDto) {

        Optional<User> result = Optional.ofNullable(
                userRepository.findById(id)
                        .orElseThrow(MemberIdNotfound::new));

        List<UserEditInputDto> userEditInputDtoList = new ArrayList<>();

        for (UserEditInputDto userEditInputDto : userInputDto.getUserEditInputDtoList()) {
            userEditInputDtoList.add(userEditInputDto.builder()
                    .userPhoneNumber(userEditInputDto.getUserPhoneNumber())
                    .userEmail(userEditInputDto.getUserEmail())
                    .userAddress(userEditInputDto.getUserAddress())
                    .build());
        }

        if (result.isPresent()) {

            userEditInputDtoList.forEach(userEditInputDto -> {
                userRepository.save(
                        User.builder()
                                .id(id)
                                .userId(result.get().getUserId())
                                .userPwd(result.get().getUserPwd())
                                .userAddress(userEditInputDto.getUserAddress())
                                .userPhone(userEditInputDto.getUserPhoneNumber())
                                .name(result.get().getName())
                                .role(result.get().getRole())
                                .userDropCheck(result.get().getUserDropCheck())
                                .userEmail(userEditInputDto.getUserEmail())
                                .memberType(result.get().getMemberType())
                                .build()
                );

            });
        }
    }

    @Override
    public User removeUserInfo(Long id, UserRemoveDto userRemoveDto) {

        Optional<User> check =
                Optional.ofNullable(
                        userRepository.findById(id).orElseThrow(
                                MemberIdNotfound::new));

        if (check.isPresent()) {
            if (userRemoveDto.getUserDropCheck().equals(true)) {
                userRepository.deleteById(id);
            } else {
                throw new UserdropCheckNotfound();
            }
        } else {
            throw new UserIdNotFound();
        }
        return null;
    }

    @Override
    public boolean changePassword(String userId, UserChangePwdInputDto userChangePwdInputDto) {

        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isPresent()) {
            userRepository.save(
                    User.builder()
                            .id(user.get().getId())
                            .userId(user.get().getUserId())
                            .userPwd(passwordEncoder.encode(userChangePwdInputDto.getUserPwd()))
                            .userAddress(user.get().getUserAddress())
                            .userPhone(user.get().getUserPhone())
                            .name(user.get().getName())
                            .role(user.get().getRole())
                            .userDropCheck(user.get().getUserDropCheck())
                            .userEmail(user.get().getUserEmail())
                            .memberType(user.get().getMemberType())
                            .build()

            );
        }
        return true;
    }

    @Override
    public boolean verifyPassword(String userId, UserChangePwdInputDto userChangePwdInputDto) {
        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isPresent() &&
                passwordEncoder.matches(userChangePwdInputDto.getUserPwd(), user.get().getUserPwd())) {

            User.builder()
                    .id(user.get().getId())
                    .userId(user.get().getUserId())
                    .userPwd(passwordEncoder.encode(userChangePwdInputDto.getUserPwd()))
                    .userAddress(user.get().getUserAddress())
                    .userPhone(user.get().getUserPhone())
                    .name(user.get().getName())
                    .role(user.get().getRole())
                    .userDropCheck(user.get().getUserDropCheck())
                    .userEmail(user.get().getUserEmail())
                    .memberType(user.get().getMemberType())
                    .build();

            return true;
        } else {

            return false;
        }
    }
}
