package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.domain.SmallCategory;
import com.spharosacademy.project.SSGBack.product.dto.SmallCategoryDto;
import com.spharosacademy.project.SSGBack.product.service.ISmallCateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/smallcate")
@RequiredArgsConstructor
public class SmallCategoryController {
    private final ISmallCateService iSmallCateService;

    @PostMapping("/add")
    public SmallCategory addSmallCategory(@RequestBody SmallCategoryDto smallCategoryDto) {
        return iSmallCateService.addSmallCategory(smallCategoryDto);
    }
}
