package com.spharosacademy.project.SSGBack.address.controller;

import com.nimbusds.openid.connect.sdk.claims.Address;
import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.address.dto.AddressListDto;
import com.spharosacademy.project.SSGBack.address.entity.AddressList;
import com.spharosacademy.project.SSGBack.address.service.AddressListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresslist")
@RequiredArgsConstructor


public class AddressListController {

    private final AddressListService addressListService;

    //배송지 추가
    @PostMapping("/add")
    public AddressList addAddressList(
            @RequestBody AddressListDto addressListDto) {
        return addressListService.addAddressList(addressListDto);
    }

    // 배송지 리스트 조회
    @GetMapping("/all")
    public List<AddressList> getAllAddressList() {
        return addressListService.getAll();
    }

    // 유저 아이디로 addressList 조회
    @GetMapping("/addresslist/{addressId}")
    public AddressListDto getAddressListById(@PathVariable int addressId) {
        return addressListService.getByAddressListId(addressId);
    }


    // 수정

    @PutMapping("/update")
    public AddressList editAddressList(@RequestBody AddressListDto addressListDto) throws Exception {
        return addressListService.editAddressListById(addressListDto);
    }


    // 삭제 ㅜ
    @DeleteMapping("/delete/{addressId}")
    public void deleteAddressById(@PathVariable int addressId) throws Exception {
        addressListService.deleteAddressListById(addressId);
    }


}
