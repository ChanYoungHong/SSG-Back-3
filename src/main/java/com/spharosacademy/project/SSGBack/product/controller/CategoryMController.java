package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.domain.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.CategoryMDto;
import com.spharosacademy.project.SSGBack.product.service.CategoryMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cateM")
@RequiredArgsConstructor
public class CategoryMController {

    private final CategoryMService categoryMService;

    @PostMapping("/add")
    public CategoryM addCategoryM(@RequestBody CategoryMDto categoryMDto){
        return categoryMService.addCategoryM(categoryMDto);

    }
}
