package com.spharosacademy.project.SSGBack.addresslist.service;

import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListDeleteDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListInputDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListUpdateDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.output.AddressListOutputDto;
import com.spharosacademy.project.SSGBack.addresslist.entity.AddressList;

import java.util.List;

public interface AddressListService {

    void addAddressList(AddressListInputDto addressListInputDto, Long userId);

    List<AddressListOutputDto> getAddressListByUserId(Long memberId);

    AddressList editAddressList(AddressListUpdateDto addressListUpdateDto, Long userId);

    void deleteAddressList(AddressListDeleteDto addressListDeleteDto, Long userId);




}
