package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategorySSDto;
import com.spharosacademy.project.SSGBack.category.dto.output.CategorySSDto;
import com.spharosacademy.project.SSGBack.category.dto.output.ProductOfCategory;
import com.spharosacademy.project.SSGBack.category.entity.CategoryProductList;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.category.repository.CategoryProductListRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.category.service.CategorySSService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CategorySSServiceimple implements CategorySSService {

    private final CategorySSRepository categorySSRepository;
    private final CategorySRepository categorySRepository;
    private final CategoryProductListRepository categoryProductListRepository;

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
    public CategorySSDto getCategorySSById(Integer id) {
        CategorySS categorySS = categorySSRepository.findById(id).get();
        List<CategoryProductList> categoryProductLists = categoryProductListRepository.findAllByCategorySS(categorySS);
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


        return CategorySSDto.builder()
                .id(categorySS.getId())
                .name(categorySS.getName())
                .productOfCategoryList(productOfCategoryList)
                .build();
    }

}
