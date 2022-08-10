package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategoryMDto;
import com.spharosacademy.project.SSGBack.category.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.category.service.CategoryMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryMServiceimple implements CategoryMService {

    private final CategoryMRepository categoryMRepository;
    private final CategoryLRepository categoryLRepository;


    @Override
    public CategoryM addCategoryM(RequestCategoryMDto categoryMDto) {

        return categoryMRepository.save(
                CategoryM.builder()
                        .name(categoryMDto.getName())
                        .categoryL(categoryLRepository.findById(categoryMDto.getCategoryLId()).get())
                        .build()
        );
    }

    @Override
    public List<CategoryM> getAll() {
        return categoryMRepository.findAll();
    }

    @Override
    public void deleteCategoryMById(Integer id) {
        categoryMRepository.deleteById(id);
    }

    @Override
    public CategoryM getCategoryMById(Integer id) {
        return categoryMRepository.findById(id).get();
    }

    @Override
    public CategoryM editCategoryM(RequestCategoryMDto categoryMDto) {
        Optional<CategoryM> categoryM = categoryMRepository.findById(categoryMDto.getId());
        if (categoryM.isPresent()) {
            categoryMRepository.save(
                    CategoryM.builder()
                            .name(categoryMDto.getName())
                            .id(categoryMDto.getId())
                            .build()
            );
        }
        return null;
    }

}

