package com.spharosacademy.project.SSGBack.address.service;

import com.spharosacademy.project.SSGBack.Qna.dto.RequestQnaDto;
import com.spharosacademy.project.SSGBack.Qna.dto.ResponseQnaDto;
import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.address.dto.AddressListDto;
import com.spharosacademy.project.SSGBack.address.entity.AddressList;

import java.util.List;

public interface AddressListService {


    AddressList addAddressList(AddressListDto addressListDto);
    List<AddressList> getAll();


    // 유저아이디로 배송지 리스트 찾기
    AddressListDto getByAddressListId(int addressId);


    // 배송지 리스트 수정
    AddressList editAddressListById(AddressListDto addressListDto) throws Exception;

    // 배송지 삭제
    void deleteAddressListById(int addressId) throws Exception;
}
