package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.LastCategory;
import com.spharosacademy.project.SSGBack.product.dto.LastCategoryDto;

public interface ILastCateService {
    LastCategory addLastCategory(LastCategoryDto lastCategoryDto);

}
