package com.spharosacademy.project.SSGBack.user.service.impl;

import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.user.dto.request.UserEditInputDto;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.UserDropCheckNotFound;
import com.spharosacademy.project.SSGBack.user.repository.UserRepository;
import com.spharosacademy.project.SSGBack.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service

public class UserServiceimple implements UserService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    private final UserRepository userRepository;

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void modifyUserInfo(Long memberId, UserOutputDto userOutputDto) {

        User result = userRepository.findById(memberId).get();

        List<UserEditInputDto> userEditInputDtoList = new ArrayList<>();

        for (UserEditInputDto userEditInputDto : userOutputDto.getUserEditInputDtoList()) {
            userEditInputDtoList.add(userEditInputDto.builder()
                    .userPhoneNumber(userEditInputDto.getUserPhoneNumber())
                    .userEmail(userEditInputDto.getUserEmail())
                    .build());
        }


        userEditInputDtoList.forEach(userEditInputDto -> {
            userRepository.save(
                    User.builder()
                            .userId(result.getUserId())
                            .userPwd(result.getUserPwd())
                            .userAddress(result.getUserAddress())
                            .userPhone(userEditInputDto.getUserPhoneNumber())
                            .userName(result.getUsername())
                            .userEmail(userEditInputDto.getUserEmail())
                            .memberType(result.getMemberType())
                            .build()
            );
        });

    }

    @Override
    public User removeUserInfo(Long memberId, UserOutputDto userOutputDto) {

        User userCheck = userRepository.findById(userOutputDto.getId()).get();

        if (userCheck != null) {
            if (userOutputDto.getUserDropCheck().equals(true)) {
                userRepository.deleteById(userOutputDto.getId());
            }
        } else {
            new UserDropCheckNotFound();
        }
        return null;
    }
}
