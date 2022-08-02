package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.CategoryMDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategoryMRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryMServiceimple implements CategoryMService{

    private final CategoryMRepository categoryMRepository;
    private final CategoryLRepository categoryLRepository;

    @Override
    public CategoryM addCategoryM(CategoryMDto categoryMDto) {
        return categoryMRepository.save(
                CategoryM.builder()
                        .name(categoryMDto.getName())
                        .categoryL(categoryLRepository.findById((long) categoryMDto.getCategoryLId()).get())
                        .build()
        );
    }
}
