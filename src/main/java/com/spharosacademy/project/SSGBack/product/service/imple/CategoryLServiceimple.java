package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategoryLDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.product.service.CategoryLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryLServiceimple implements CategoryLService {

    private final CategoryLRepository categoryLRepository;

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
    public CategoryL getCategoryLById(Integer id) {
        return categoryLRepository.findById(id).get();
    }


    @Override
    public void deleteCategoryLById(Integer id) {
        categoryLRepository.deleteById(id);
    }
}
