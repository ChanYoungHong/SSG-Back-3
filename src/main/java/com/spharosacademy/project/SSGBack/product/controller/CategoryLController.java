package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.domain.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.service.CategoryLService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CateL")
@RequiredArgsConstructor
public class CategoryLController {

    private final CategoryLService categoryLService;

    @PostMapping("/add")
    public CategoryL addCategoryL(@RequestBody CategoryLDto categoryLDto){
        return categoryLService.addCategoryL(categoryLDto);
    }

}
