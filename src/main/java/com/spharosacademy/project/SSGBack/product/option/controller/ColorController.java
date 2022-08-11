package com.spharosacademy.project.SSGBack.product.option.controller;

import com.spharosacademy.project.SSGBack.product.option.dto.CreateColorDto;
import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;
import com.spharosacademy.project.SSGBack.product.option.sevice.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @PostMapping("/insert")
    public ColorOption addColor(
            @RequestBody CreateColorDto createColorDto){
        return colorService.createColor(createColorDto);
    }
}
