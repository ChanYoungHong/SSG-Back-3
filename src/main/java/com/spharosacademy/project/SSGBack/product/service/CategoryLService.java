package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategoryLDto;

import java.util.List;

public interface CategoryLService {
    CategoryL addCategoryL(RequestCategoryLDto categoryLDto);

    List<CategoryL> getAll();

    CategoryL getCategoryLById(Integer id);

    CategoryL editCategoryL(RequestCategoryLDto categoryLDto);

    void deleteCategoryLById(Integer id);

}
