package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategorySDto;

import java.util.List;

public interface CategorySService {

    CategoryS addCategoryS(RequestCategorySDto categorySDto);

    List<CategoryS> getAll();

    void deleteCategorySById(Integer id);

    CategoryS editCategoryS(RequestCategorySDto categorySDto);

    CategoryS getCategorySById(Integer id);
}
