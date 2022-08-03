package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.input.CategoryLDto;
import com.spharosacademy.project.SSGBack.user.domain.User;

import java.util.List;

public interface CategoryLService {
    CategoryL addCategoryL(CategoryLDto categoryLDto);

    List<CategoryL> getAll();

    CategoryL getCategoryLById(Integer id);

    CategoryL editCategoryL(Integer id, CategoryLDto categoryLDto);

    void deleteCategoryLById(Integer id);

}
