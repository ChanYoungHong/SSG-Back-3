package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategorySSDto;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.category.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.category.service.CategorySSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CategorySSServiceimple implements CategorySSService {

    private final CategorySSRepository categorySSRepository;
    private final CategorySRepository categorySRepository;
    
    @Override
    public CategorySS addCategorySS(RequestCategorySSDto categorySSDto) {
        return categorySSRepository.save(
                CategorySS.builder()
                        .name(categorySSDto.getName())
                        .categoryS(categorySRepository.findById(categorySSDto.getCategorySId()).get())
                        .build()
        );
    }

    @Override
    public List<CategorySS> getAll() {
        return categorySSRepository.findAll();
    }

    @Override
    public void deleteCategorySSById(Integer id) {
        categorySSRepository.deleteById(id);
    }

    @Override
    public CategorySS editCategorySS(RequestCategorySSDto categorySSDto) {
        Optional<CategorySS> categorySS = categorySSRepository.findById(categorySSDto.getId());
        if (categorySS.isPresent()) {
            categorySSRepository.save(
                    CategorySS.builder()
                            .id(categorySSDto.getId())
                            .name(categorySSDto.getName())
                            .build()
            );
        }

        return null;
    }


    @Override
    public CategorySS getCategorySSById(Integer id) {
        return categorySSRepository.findById(id).get();
    }

}
