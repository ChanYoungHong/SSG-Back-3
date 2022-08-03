package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.input.CategorySDto;
import com.spharosacademy.project.SSGBack.product.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.product.service.CategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategorySServiceimple implements CategorySService {

    private final CategorySRepository categorySRepository;
    private final CategoryMRepository categoryMRepository;

    @Override
    public CategoryS addCategoryS(CategorySDto categorySDto) {
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
    public CategoryS editCategoryS(Integer id, CategorySDto categorySDto) {
        return categorySRepository.save(
                CategoryS.builder()
                        .name(categorySDto.getName())
                        .id(categorySDto.getId())
                        .build()
        );
    }


    @Override
    public CategoryS getCategorySById(Integer id) {
        return categorySRepository.findById(id).get();
    }


}
