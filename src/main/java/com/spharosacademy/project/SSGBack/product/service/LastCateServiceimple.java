package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.LastCategory;
import com.spharosacademy.project.SSGBack.product.dto.LastCategoryDto;
import com.spharosacademy.project.SSGBack.product.repository.ILastCategoryRepository;
import com.spharosacademy.project.SSGBack.product.repository.ISmallCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LastCateServiceimple implements ILastCateService{
    private final ILastCategoryRepository iLastCategoryRepository;
    private final ISmallCategoryRepository iSmallCategoryRepository;

    @Override
    public LastCategory addLastCategory(LastCategoryDto lastCategoryDto) {
        return iLastCategoryRepository.save(
                LastCategory.builder()
                        .name(lastCategoryDto.getName())
                        .smallCategory(iSmallCategoryRepository.findById(lastCategoryDto.getSmallCategoryId()).get())
                        .build()
        );
    }
}
