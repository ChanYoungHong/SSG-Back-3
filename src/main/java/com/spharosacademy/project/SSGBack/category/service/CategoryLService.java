package com.spharosacademy.project.SSGBack.category.service;

import com.spharosacademy.project.SSGBack.category.entity.CategoryL;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryLDto;
import com.spharosacademy.project.SSGBack.category.dto.output.CategoryLDto;

import java.util.List;

public interface CategoryLService {
    CategoryL addCategoryL(RequestCategoryLDto categoryLDto);

    List<CategoryL> getAll();

    CategoryLDto getCategoryLById(Integer id);

    CategoryL editCategoryL(RequestCategoryLDto categoryLDto);

    void deleteCategoryLById(Integer id);
}
