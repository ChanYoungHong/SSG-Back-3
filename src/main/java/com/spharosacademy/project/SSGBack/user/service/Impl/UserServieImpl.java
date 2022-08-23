package com.spharosacademy.project.SSGBack.user.service.Impl;

import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServieImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
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
//                        .userBirthDate(result.get().getUserBirthDate())
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
                userRepository.findById(userOutputDto.getId()).orElseThrow(
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
