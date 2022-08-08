package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.entity.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategoryMDto;

import java.util.List;

public interface CategoryMService {
    CategoryM addCategoryM(RequestCategoryMDto categoryMDto);

    List<CategoryM> getAll();

    void deleteCategoryMById(Integer id);

    CategoryM getCategoryMById(Integer id);

    CategoryM editCategoryM(RequestCategoryMDto categoryMDto);
}
