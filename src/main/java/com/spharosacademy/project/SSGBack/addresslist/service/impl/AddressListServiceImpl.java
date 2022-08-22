package com.spharosacademy.project.SSGBack.addresslist.service.impl;

import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListDeleteDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListInputDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListUpdateDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.output.AddressListOutputDto;
import com.spharosacademy.project.SSGBack.addresslist.entity.AddressList;
import com.spharosacademy.project.SSGBack.addresslist.repository.AddressListRepository;
import com.spharosacademy.project.SSGBack.addresslist.service.AddressListService;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.repository.UserRepository;
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

    @Override
    public void addAddressList(AddressListInputDto addressListInputDto) {
        User user = userRepository.findById(addressListInputDto.getUserId()).get();
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
    public AddressList editAddressList(AddressListUpdateDto addressListUpdateDto) {
        User user = userRepository.findById(addressListUpdateDto.getUserId()).get();
        AddressList addressList = addressListRepository.save(AddressList.builder()
                .user(user)
                        .id(addressListUpdateDto.getAddressId())
                .addressNickname(addressListUpdateDto.getAddressNickname())
                .addressReceiver(addressListUpdateDto.getAddressReceiver())
                .addressHomeNumber(addressListUpdateDto.getAddressHomeNumber())
                .addressHomeNumber(addressListUpdateDto.getAddressHomeNumber())
                .build());
        return addressList;
    }

    @Override
    public void deleteAddressList(AddressListDeleteDto addressListDeleteDto) {
        addressListRepository.deleteById(addressListDeleteDto.getAddressId());

    }
}
