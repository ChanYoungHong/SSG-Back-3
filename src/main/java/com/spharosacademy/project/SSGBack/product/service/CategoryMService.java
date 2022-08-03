package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.entity.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.input.CategoryMDto;

import java.util.List;

public interface CategoryMService {
    CategoryM addCategoryM(CategoryMDto categoryMDto);

    List<CategoryM> getAll();

    void deleteCategoryMById(Integer id);

    CategoryM getCategoryMById(Integer id);

    CategoryM editCategoryM(Integer id, CategoryMDto categoryMDto);
}
