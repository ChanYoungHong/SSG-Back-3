package com.spharosacademy.project.SSGBack.user.service.Impl;

import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.MemberIdNotfound;
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
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean duplicateUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
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
                .build());
        }

        if (result.isPresent()) {

            userEditInputDtoList.forEach(userEditInputDto -> {
                userRepository.save(
                    User.builder()
                        .id(id)
                        .userId(result.get().getUserId())
                        .userPwd(result.get().getUserPwd())
                        .userAddress(result.get().getUserAddress())
                        .userPhone(userEditInputDto.getUserPhoneNumber())
                        .userName(result.get().getUsername())
                        .role(result.get().getRole())
                        .userDropCheck(result.get().getUserDropCheck())
                        .userEmail(userEditInputDto.getUserEmail())
                        .memberType(result.get().getMemberType())
//                        .userPwd(passwordEncoder.encode(userInputDto.getUserPwd()))
                        .build()
                );

            });
        }
    }

    @Override
    public User removeUserInfo(Long id, UserOutputDto userOutputDto) {

        Optional<User> check =
            Optional.ofNullable(
                userRepository.findById(id).orElseThrow(
                    MemberIdNotfound::new));

        if (check.isPresent()) {
            if (userOutputDto.getUserDropCheck().equals(true)) {
                userRepository.deleteById(userOutputDto.getId());
            }
        } else {
            new UserdropCheckNotfound();
        }
        return null;
    }

}
