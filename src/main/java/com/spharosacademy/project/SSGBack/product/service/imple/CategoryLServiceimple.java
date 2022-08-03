package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.input.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.product.service.CategoryLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryLServiceimple implements CategoryLService {

    private final CategoryLRepository categoryLRepository;

    @Override
    public CategoryL addCategoryL(CategoryLDto categoryLDto) {
        return categoryLRepository.save(
                CategoryL.builder()
                        .name(categoryLDto.getName())
                        .build()
        );
    }

    @Override
    public CategoryL editCategoryL(Integer id, CategoryLDto categoryLDto) {
        return categoryLRepository.save(
                CategoryL.builder()
                        .name(categoryLDto.getName())
                        .id(categoryLDto.getId())
                        .build()
        );
    }


    @Override
    public List<CategoryL> getAll() {
        return categoryLRepository.findAll();
    }

    @Override
    public CategoryL getCategoryLById(Integer id) {
        return categoryLRepository.findById(id).get();
    }


    @Override
    public void deleteCategoryLById(Integer id) {
        categoryLRepository.deleteById(id);
    }
}
