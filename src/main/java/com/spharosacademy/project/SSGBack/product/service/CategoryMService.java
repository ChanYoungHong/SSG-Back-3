package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.CategoryL;
import com.spharosacademy.project.SSGBack.product.domain.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.CategoryMDto;

public interface CategoryMService {
    CategoryM addCategoryM(CategoryMDto categoryMDto);

    CategoryM getCategoryMById(Integer id);
}
