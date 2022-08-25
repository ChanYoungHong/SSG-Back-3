package com.spharosacademy.project.SSGBack.addresslist.controller;


import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListDeleteDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListInputDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.input.AddressListUpdateDto;
import com.spharosacademy.project.SSGBack.addresslist.dto.output.AddressListOutputDto;
import com.spharosacademy.project.SSGBack.addresslist.service.AddressListService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/addresslist")
@RequiredArgsConstructor
@CrossOrigin
public class AddressListController {

    private final AddressListService addressListService;
    private final JwtTokenProvider jwtTokenProvider;

    // 주소 추가
    @PostMapping("/add")
    public String addAddressList(@RequestBody AddressListInputDto addressListInputDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));

        addressListService.addAddressList(addressListInputDto, userId);
        return "주소가 추가 되었습니다";
    }

    @GetMapping("/getByUserId")
    public List<AddressListOutputDto> addressListOutputDtos() {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));

        return addressListService.getAddressListByUserId(userId);
    }


    @PutMapping("/edit")
    public String editAddressList(@RequestBody AddressListUpdateDto addressListUpdateDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));

        addressListService.editAddressList(addressListUpdateDto, userId);
        return "주소가 수정 되었습니다";
    }

    @DeleteMapping("/delete")
    public String deleteAddressList(@RequestBody AddressListDeleteDto addressListDeleteDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));

        addressListService.deleteAddressList(addressListDeleteDto, userId);
        return "주소가 삭제되었습니다";
    }



}
