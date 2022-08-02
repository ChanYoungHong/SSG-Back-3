package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.domain.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.CategorySDto;
import com.spharosacademy.project.SSGBack.product.service.CategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CateS")
@RequiredArgsConstructor
public class CategorySController {
    private final CategorySService categorySService;

    @PostMapping("/add")
    public CategoryS addCategoryS(@RequestBody CategorySDto CategorySDto) {
        return categorySService.addCategoryS(CategorySDto);
    }
}
