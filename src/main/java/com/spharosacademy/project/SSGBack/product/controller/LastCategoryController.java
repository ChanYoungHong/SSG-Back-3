package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.domain.LastCategory;
import com.spharosacademy.project.SSGBack.product.dto.LastCategoryDto;
import com.spharosacademy.project.SSGBack.product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.product.service.ILastCateService;
import com.spharosacademy.project.SSGBack.product.service.IProductService;
import com.spharosacademy.project.SSGBack.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lastCate")
@RequiredArgsConstructor
public class LastCategoryController {

    private final ILastCateService iLastCateService;

    @PostMapping("/add")
    public LastCategory addLastCategory(@RequestBody LastCategoryDto lastCategoryDto){
        return iLastCateService.addLastCategory(lastCategoryDto);
    }
}
