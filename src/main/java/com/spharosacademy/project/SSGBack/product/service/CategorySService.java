package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.input.CategorySDto;

import java.util.List;

public interface CategorySService {

    CategoryS addCategoryS(CategorySDto categorySDto);

    List<CategoryS> getAll();

    void deleteCategorySById(Integer id);

    CategoryS editCategoryS(Integer id, CategorySDto categorySDto);

    CategoryS getCategorySById(Integer id);
}
