package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategorySSDto;
import com.spharosacademy.project.SSGBack.product.entity.CategorySS;

import java.util.List;

public interface CategorySSService {
    CategorySS addCategorySS(RequestCategorySSDto categorySSDto);

    List<CategorySS> getAll();

    void deleteCategorySSById(Integer id);

    CategorySS editCategorySS(RequestCategorySSDto categorySSDto);

    CategorySS getCategorySSById(Integer id);
}
