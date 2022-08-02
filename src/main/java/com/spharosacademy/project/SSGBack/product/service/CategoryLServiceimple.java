package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryLServiceimple implements CategoryLService{
    private final ProductRepository productRepository;
    private final CategoryLRepository categoryLRepository;

    @Override
    public CategoryL addCategoryL(CategoryLDto categoryLDto) {
        return categoryLRepository.save(
                CategoryL.builder()
                        .name(categoryLDto.getName())
                        .build()
        );
    }
}
