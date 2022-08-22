package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.category.dto.output.CategorySDto;
import com.spharosacademy.project.SSGBack.category.dto.output.CategorySSOfCategorySDto;
import com.spharosacademy.project.SSGBack.category.dto.output.ProductOfCategory;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.entity.CategoryProductList;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategorySDto;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.category.exception.CategoryNotFoundException;
import com.spharosacademy.project.SSGBack.category.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategoryProductListRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.category.service.CategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategorySServiceimple implements CategorySService {

    private final CategorySRepository categorySRepository;
    private final CategoryMRepository categoryMRepository;
    private final CategoryProductListRepository categoryProductListRepository;
    private final CategorySSRepository categorySSRepository;

    @Override
    public CategoryS addCategoryS(RequestCategorySDto categorySDto) {
        return categorySRepository.save(
                CategoryS.builder()
                        .name(categorySDto.getName())
                        .categoryM(categoryMRepository.findById(categorySDto.getCategoryMId())
                                .orElseThrow(CategoryNotFoundException::new))
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
        categoryMRepository.findById(categorySDto.getCategoryMId()).get().getId();
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
    public CategorySDto getCategorySById(Integer id) {

        CategoryS categoryS = categorySRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        List<CategoryProductList> categoryProductLists = categoryProductListRepository.findAllByCategoryS(categoryS);
        List<CategorySS> ssList = categorySSRepository.findAllByCategoryS(categoryS);
        List<CategorySSOfCategorySDto> categorySDtos = new ArrayList<>();

        for (CategorySS categorySSob : ssList) {
            categorySDtos.add(CategorySSOfCategorySDto.builder()
                    .id(categorySSob.getId())
                    .name(categorySSob.getName())
                    .build());
        }

        List<ProductOfCategory> productOfCategoryList = new ArrayList<>();
        for (CategoryProductList categoryProductList : categoryProductLists) {
            productOfCategoryList.add(ProductOfCategory.builder()
                    .id(categoryProductList.getProduct().getId())
                    .name(categoryProductList.getProduct().getName())
                    .id(categoryProductList.getProduct().getId())
                    .brand(categoryProductList.getProduct().getBrand())
                    .mallTxt(categoryProductList.getProduct().getMallText())
                    .price(categoryProductList.getProduct().getNewPrice())
                    .thumbnailUrl(categoryProductList.getProduct().getThumbnailUrl())
                    .build());
        }
        return CategorySDto.builder()
                .name(categoryS.getName())
                .id(categoryS.getId())
                .categorySSOfCategorySDtoList(categorySDtos)
                .productOfCategoryList(productOfCategoryList)
                .build();
    }


}
