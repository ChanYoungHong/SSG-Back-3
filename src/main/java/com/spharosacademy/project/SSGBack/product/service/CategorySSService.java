package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.dto.input.CategorySSDto;
import com.spharosacademy.project.SSGBack.product.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.entity.CategorySS;

import java.util.List;

public interface CategorySSService {
    CategorySS addCategorySS(CategorySSDto categorySSDto);

    List<CategorySS> getAll();

    void deleteCategorySSById(Integer id);

    CategorySS editCategorySS(Integer id, CategorySSDto categorySSDto);

    CategorySS getCategorySSById(Integer id);
}
