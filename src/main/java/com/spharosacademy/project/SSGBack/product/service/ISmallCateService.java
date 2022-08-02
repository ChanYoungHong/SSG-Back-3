package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.SmallCategory;
import com.spharosacademy.project.SSGBack.product.dto.SmallCategoryDto;

public interface ISmallCateService {

    SmallCategory addSmallCategory(SmallCategoryDto smallCategoryDto);
}
