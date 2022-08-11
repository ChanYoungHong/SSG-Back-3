package com.spharosacademy.project.SSGBack.product.option.controller;

import com.spharosacademy.project.SSGBack.product.option.dto.CreateSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.SizeOption;
import com.spharosacademy.project.SSGBack.product.option.sevice.SizeService;
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

    @PostMapping("/insert")
    public SizeOption addSize(@RequestBody CreateSizeDto createSizeDto){
        return sizeService.createSize(createSizeDto);
    }

}
