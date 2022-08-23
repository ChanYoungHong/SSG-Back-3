package com.spharosacademy.project.SSGBack.addresslist.controller;


import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListDeleteDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListInputDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListUpdateDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.output.AddressListOutputDto;
import com.spharosacademy.project.SSGBack.addresslist.service.AddressListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/addresslist")
@RequiredArgsConstructor
@CrossOrigin
public class AddressListController {

    private final AddressListService addressListService;

    // 주소 추가
    @PostMapping("/add")
    public String addAddressList(@RequestBody AddressListInputDto addressListInputDto) {
        addressListService.addAddressList(addressListInputDto);
        return "주소가 추가 되었습니다";
    }

    @GetMapping("/{userId}")
    public List<AddressListOutputDto> addressListOutputDtos(@PathVariable Long userId) {
        return addressListService.getAddressListByUserId(userId);
    }


    @PutMapping("/edit")
    public String editAddressList(@RequestBody AddressListUpdateDto addressListUpdateDto) {
        addressListService.editAddressList(addressListUpdateDto);
        return "주소가 수정 되었습니다";
    }

    @DeleteMapping("/delete")
    public String deleteAddressList(@RequestBody AddressListDeleteDto addressListDeleteDto) {
        addressListService.deleteAddressList(addressListDeleteDto);
        return "주소가 삭제되었습니다";
    }



}
