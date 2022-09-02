package com.spharosacademy.project.SSGBack.category.service;

import com.spharosacademy.project.SSGBack.category.dto.output.CategoryMDto;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryMDto;

import java.util.List;

public interface CategoryMService {
    CategoryM addCategoryM(RequestCategoryMDto categoryMDto);

    List<CategoryM> getAll();

    void deleteCategoryMById(Integer id);

    CategoryMDto getCategoryMById(Integer id, Long userId);

    CategoryM editCategoryM(RequestCategoryMDto categoryMDto);
}
