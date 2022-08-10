package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategorySDto;
import com.spharosacademy.project.SSGBack.category.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.category.service.CategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategorySServiceimple implements CategorySService {

    private final CategorySRepository categorySRepository;
    private final CategoryMRepository categoryMRepository;

    @Override
    public CategoryS addCategoryS(RequestCategorySDto categorySDto) {
        return categorySRepository.save(
                CategoryS.builder()
                        .name(categorySDto.getName())
                        .categoryM(categoryMRepository.findById(categorySDto.getCategoryMId()).get())
                        .build()
        );
    }

    @Override
    public List<CategoryS> getAll() {
        return categorySRepository.findAll();
    }

    @Override
    public void deleteCategorySById(Integer id) {
        categorySRepository.deleteById(id);
    }

    @Override
    public CategoryS editCategoryS(RequestCategorySDto categorySDto) {

        Optional<CategoryM> categoryS = categoryMRepository.findById(categorySDto.getId());
        if (categoryS.isPresent()) {
            categorySRepository.save(
                    CategoryS.builder()
                            .name(categorySDto.getName())
                            .id(categorySDto.getId())
                            .build()
            );
        }
        return null;
    }


    @Override
    public CategoryS getCategorySById(Integer id) {
        return categorySRepository.findById(id).get();
    }


}
