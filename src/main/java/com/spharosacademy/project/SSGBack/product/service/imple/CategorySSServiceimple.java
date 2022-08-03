package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.dto.input.CategorySSDto;
import com.spharosacademy.project.SSGBack.product.entity.CategorySS;
import com.spharosacademy.project.SSGBack.product.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.product.service.CategorySSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CategorySSServiceimple implements CategorySSService {

    private final CategorySSRepository categorySSRepository;
    private final CategorySRepository categorySRepository;
    @Override
    public CategorySS addCategorySS(CategorySSDto categorySSDto) {
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
    public CategorySS editCategorySS(Integer id, CategorySSDto categorySSDto) {
        return categorySSRepository.save(
                CategorySS.builder()
                        .name(categorySSDto.getName())
                        .id(categorySSDto.getId())
                        .build()
        );
    }


    @Override
    public CategorySS getCategorySSById(Integer id) {
        return categorySSRepository.findById(id).get();
    }

}
