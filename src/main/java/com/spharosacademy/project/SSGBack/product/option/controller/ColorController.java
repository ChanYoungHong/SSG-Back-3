package com.spharosacademy.project.SSGBack.product.option.controller;

import com.spharosacademy.project.SSGBack.product.option.dto.input.RequestColorDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Colors;
import com.spharosacademy.project.SSGBack.product.option.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping("/add")
    public Colors addColor(@RequestBody RequestColorDto requestColorDto){
        return colorService.addColor(requestColorDto);
    }
}
