package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.entity.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.input.CategoryMDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.product.service.CategoryMService;
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
    public CategoryM editCategoryM(Integer id, CategoryMDto categoryMDto) {
        return categoryMRepository.save(
                CategoryM.builder()
                        .name(categoryMDto.getName())
                        .id(categoryMDto.getId())
                        .build()
        );
    }

}
