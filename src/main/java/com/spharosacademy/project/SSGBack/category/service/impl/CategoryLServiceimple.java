package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.category.dto.output.CategoryMofCategoryLDto;
import com.spharosacademy.project.SSGBack.category.entity.CategoryL;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryLDto;
import com.spharosacademy.project.SSGBack.category.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.category.service.CategoryLService;
import com.spharosacademy.project.SSGBack.category.dto.output.CategoryLDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryLServiceimple implements CategoryLService {

    private final CategoryLRepository categoryLRepository;
    private final CategoryMRepository categoryMRepository;

    @Override
    public CategoryL addCategoryL(RequestCategoryLDto categoryLDto) {
        return categoryLRepository.save(
                CategoryL.builder()
                        .name(categoryLDto.getName())
                        .build()
        );
    }

    @Override
    public CategoryL editCategoryL(RequestCategoryLDto categoryLDto) {
        Optional<CategoryL> categoryL = categoryLRepository.findById(categoryLDto.getId());
        if (categoryL.isPresent()) {
            return categoryLRepository.save(
                    CategoryL.builder()
                            .name(categoryLDto.getName())
                            .id(categoryLDto.getId())
                            .build()
            );
        }
        return null;
    }


    @Override
    public List<CategoryL> getAll() {
        return categoryLRepository.findAll();
    }

    @Override
    public CategoryLDto getCategoryLById(Integer id) {
        CategoryL categoryL = categoryLRepository.findById(id).get();

        List<CategoryM> categoryMList = categoryMRepository.findAllByCategoryL(categoryL);
        List<CategoryMofCategoryLDto> categoryMofCategoryLDtos = new ArrayList<>();

        for (CategoryM categoryM : categoryMList) {
            categoryMofCategoryLDtos.add(CategoryMofCategoryLDto.builder()
                    .id(categoryM.getId())
                    .name(categoryM.getName())
                    .build());
        }

        return CategoryLDto.builder()
                .name(categoryL.getName())
                .id(categoryL.getId())
                .categoryMofCategoryLDtos(categoryMofCategoryLDtos)
                .build();
    }


    @Override
    public void deleteCategoryLById(Integer id) {
        categoryLRepository.deleteById(id);
    }
}

