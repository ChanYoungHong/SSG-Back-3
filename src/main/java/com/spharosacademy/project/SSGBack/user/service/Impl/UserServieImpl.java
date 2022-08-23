package com.spharosacademy.project.SSGBack.user.service.Impl;

import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
import com.spharosacademy.project.SSGBack.user.dto.request.UserInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
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
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User registerUser(UserInputDto userInputDto) {

        return userRepository.save(
            User.builder()
                .userId(userInputDto.getUserId())
                .userPwd(passwordEncoder.encode(userInputDto.getUserPwd()))
                .userAddress(userInputDto.getUserAddress())
                .userName(userInputDto.getUserName())
                .userEmail(userInputDto.getUserEmail())
                .role(UserRole.USER)
                .userPhone(userInputDto.getUserPhoneNumber())
                .userBirthDate(userInputDto.getUserBirthDate())
                .userDropCheck(userInputDto.getUserDropCheck())
                .gender(userInputDto.getGender())
                .memberType(userInputDto.getMemberType())
                .build()
        );
    }

    @Override
    public List<User> findAllByUserId(String userId) {
        return userRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void modifyUserInfo(Long memberId, UserOutputDto userOutputDto) {

        Optional<User> result = Optional.ofNullable(
            userRepository.findById(memberId)
                .orElseThrow(MemberIdNotfound::new));

        List<UserEditInputDto> userEditInputDtoList = new ArrayList<>();

        for (UserEditInputDto userEditInputDto : userOutputDto.getUserEditInputDtoList()) {
            userEditInputDtoList.add(userEditInputDto.builder()
                .userPhoneNumber(userEditInputDto.getUserPhoneNumber())
                .userEmail(userEditInputDto.getUserEmail())
                .build());
        }

        if (result.isPresent()) {

            userEditInputDtoList.forEach(userEditInputDto -> {
                userRepository.save(
                    User.builder()

                        .userId(result.get().getUserId())
                        .userPwd(result.get().getUserPwd())
                        .userAddress(result.get().getUserAddress())
                        .userPhone(userEditInputDto.getUserPhoneNumber())
                        .userName(result.get().getUsername())
                        .userEmail(userEditInputDto.getUserEmail())
                        .userBirthDate(result.get().getUserBirthDate())
                        .gender(result.get().getGender())
                        .memberType(result.get().getMemberType())
                        .build()
                );
            });
        }
    }

    @Override
    public User removeUserInfo(Long memberId, UserOutputDto userOutputDto) {

        Optional<User> check =
            Optional.ofNullable(
                userRepository.findById(userOutputDto.getMemberId()).orElseThrow(
                    MemberIdNotfound::new));

        if (check.isPresent()) {
            if (userOutputDto.getUserDropCheck().equals(true)) {
                userRepository.deleteById(userOutputDto.getMemberId());
            }
        } else {
            new UserdropCheckNotfound();
        }
        return null;
    }

}
