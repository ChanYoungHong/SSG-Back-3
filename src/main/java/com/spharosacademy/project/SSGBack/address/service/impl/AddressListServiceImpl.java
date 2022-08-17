package com.spharosacademy.project.SSGBack.address.service.impl;

import com.spharosacademy.project.SSGBack.address.dto.AddressListDto;
import com.spharosacademy.project.SSGBack.address.entity.AddressList;
import com.spharosacademy.project.SSGBack.address.exception.AddressNotFoundException;
import com.spharosacademy.project.SSGBack.address.repository.AddressListRepo;
import com.spharosacademy.project.SSGBack.address.service.AddressListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor


public class AddressListServiceImpl implements AddressListService {

    private final AddressListRepo addressListRepo;

    //추가
    @Override
    public AddressList addAddressList(AddressListDto addressListDto) {
        AddressList addressList = addressListRepo.save(
                AddressList.builder()
                        .addressNickname(addressListDto.getAddressNickname())
                        .addressReceiver(addressListDto.getAddressReceiver())
                        .addressPhoneNumber(addressListDto.getAddressPhoneNumber())
                        .addressHomeNumber(addressListDto.getAddressHomeNumber())
                        .build());
        return addressList;
    }

    //전체 배송지 조회
    @Override
    public List<AddressList> getAll() {
        List<AddressList> ListAddressList = addressListRepo.findAll();
        return ListAddressList;
    }

    @Override
    public AddressListDto getByAddressListId(int addressId) {
        return null;
    }

    //배송지 찾기



    @Override
    public AddressList editAddressListById(AddressListDto addressListDto) {
        AddressList addressList = addressListRepo.findById(addressListDto.getAddressId())
                .orElseThrow(AddressNotFoundException::new);
        addressListRepo.save(
                AddressList.builder()
                        .addressNickname(addressListDto.getAddressNickname())
                        .addressReceiver(addressList.getAddressReceiver())
                        .addressHomeNumber(addressList.getAddressHomeNumber())
                        .addressPhoneNumber(addressList.getAddressPhoneNumber())
                        .addressStatus(addressListDto.isAddressStatus())
                        .build());
        return addressList;
    }

    @Override
    public void deleteAddressListById(int addressId) throws Exception {
        Optional<AddressList> deleteById = addressListRepo.findById(addressId);
        if (deleteById.isPresent()) {
            addressListRepo.deleteById(addressId);
        } else {
            throw new Exception();
        }

    }
}
