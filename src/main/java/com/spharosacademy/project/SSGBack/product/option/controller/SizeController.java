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
    public Size addSize(@RequestBody RequestSizeDto requestSizeDto){
        return sizeService.addSize(requestSizeDto);
    }
}
