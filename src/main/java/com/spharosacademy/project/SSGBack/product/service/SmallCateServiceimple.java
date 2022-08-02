package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.SmallCategory;
import com.spharosacademy.project.SSGBack.product.dto.SmallCategoryDto;
import com.spharosacademy.project.SSGBack.product.repository.IMiddleCategoryRepository;
import com.spharosacademy.project.SSGBack.product.repository.ISmallCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SmallCateServiceimple implements ISmallCateService {

    private final ISmallCategoryRepository iSmallCategoryRepository;
    private final IMiddleCategoryRepository iMiddleCategoryRepository;

    @Override
    public SmallCategory addSmallCategory(SmallCategoryDto smallCategoryDto) {
        return iSmallCategoryRepository.save(SmallCategory.builder()
                .name(smallCategoryDto.getName())
                .middleCategory(iMiddleCategoryRepository.findById(smallCategoryDto.getMiddleCategoryId()).get())
                .build());
    }
}
