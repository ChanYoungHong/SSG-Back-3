package com.spharosacademy.project.SSGBack.addresslist.service.impl;

import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListDeleteDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListInputDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListUpdateDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.output.AddressListOutputDto;
import com.spharosacademy.project.SSGBack.addresslist.entity.AddressList;
import com.spharosacademy.project.SSGBack.addresslist.repository.AddressListRepository;
import com.spharosacademy.project.SSGBack.addresslist.service.AddressListService;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressListServiceImpl implements AddressListService {

    private final AddressListRepository addressListRepository;
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void addAddressList(AddressListInputDto addressListInputDto, Long userId) {

        User user = userRepository.findById(userId).get();
        addressListRepository.save(AddressList.builder()
                .user(user)
                .addressNickname(addressListInputDto.getAddressNickname())
                .addressReceiver(addressListInputDto.getAddressReceiver())
                .addressPhoneNumber(addressListInputDto.getAddressPhoneNumber())
                .addressHomeNumber(addressListInputDto.getAddressHomeNumber())
                .build());
    }

    @Override
    public List<AddressListOutputDto> getAddressListByUserId(Long userId) {
        List<AddressList> addressLists = addressListRepository.findAllByUserId(userId);
        List<AddressListOutputDto> addressListOutputDtos = new ArrayList<>();

        addressLists.forEach(addressList -> {
            addressListOutputDtos.add(AddressListOutputDto.builder()
                    .addressId(addressList.getId())
                    .userId(addressList.getUser().getId())
                    .addressNickname(addressList.getAddressNickname())
                    .addressReceiver(addressList.getAddressReceiver())
                    .addressHomeNumber(addressList.getAddressHomeNumber())
                    .addressPhoneNumber(addressList.getAddressPhoneNumber())
                    .build());
        });
        return addressListOutputDtos;
    }

    @Override
    public AddressList editAddressList(AddressListUpdateDto addressListUpdateDto, Long userId) {
        User user = userRepository.findById(userId).get();
        AddressList addressList = addressListRepository.save(AddressList.builder()
                .user(user)
                        .id(addressListUpdateDto.getAddressId())
                .addressNickname(addressListUpdateDto.getAddressNickname())
                .addressReceiver(addressListUpdateDto.getAddressReceiver())
                .addressPhoneNumber(addressListUpdateDto.getAddressPhoneNumber())
                .addressHomeNumber(addressListUpdateDto.getAddressHomeNumber())
                .build());
        return addressList;
    }

    @Override
    public void deleteAddressList(AddressListDeleteDto addressListDeleteDto, Long userId) {
        addressListRepository.deleteById(addressListDeleteDto.getAddressId());

    }
}
