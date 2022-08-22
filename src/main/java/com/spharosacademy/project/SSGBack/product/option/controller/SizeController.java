package com.spharosacademy.project.SSGBack.product.option.controller;

import com.spharosacademy.project.SSGBack.product.option.dto.input.RequestSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Size;
import com.spharosacademy.project.SSGBack.product.option.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @PostMapping("/add")
    public String addSize(@RequestBody RequestSizeDto requestSizeDto){
        sizeService.addSize(requestSizeDto);
        return "사이즈 정보가 등록되었습니다";
    }
}
