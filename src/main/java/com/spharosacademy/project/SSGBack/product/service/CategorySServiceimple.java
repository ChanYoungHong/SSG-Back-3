package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.CategorySDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategorySRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategorySServiceimple implements CategorySService {

    private final CategorySRepository categorySRepository;
    private final CategoryMRepository categoryMRepository;

    @Override
    public CategoryS addCategoryS(CategorySDto categorySDto) {
        return categorySRepository.save(
                CategoryS.builder()
                        .name(categorySDto.getName())
                        .categoryM(categoryMRepository.findById(categorySDto.getCategoryMId()).get())
                        .build()
        );
    }
}
