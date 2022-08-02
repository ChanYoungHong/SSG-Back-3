package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.CategoryL;
import com.spharosacademy.project.SSGBack.product.domain.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.CategoryMDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategoryMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryMServiceimple implements CategoryMService {

    private final CategoryMRepository categoryMRepository;
    private final CategoryLRepository categoryLRepository;



    @Override
    public CategoryM addCategoryM(CategoryMDto categoryMDto) {
        List<CategoryM> categoryMList;

        return categoryMRepository.save(
                CategoryM.builder()
                        .name(categoryMDto.getName())
                        .categoryL(categoryLRepository.findById(categoryMDto.getCategoryLId()).get())
                        .build()
        );
    }

    @Override
    public CategoryM getCategoryMById(Integer id) {
        return null;
    }

}

