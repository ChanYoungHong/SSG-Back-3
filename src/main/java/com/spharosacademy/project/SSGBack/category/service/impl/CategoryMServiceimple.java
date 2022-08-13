package com.spharosacademy.project.SSGBack.category.service.impl;

import com.spharosacademy.project.SSGBack.category.dto.output.CategoryMDto;
import com.spharosacademy.project.SSGBack.category.dto.output.CategorySofCategoryMDto;
import com.spharosacademy.project.SSGBack.category.dto.output.ProductOfCategory;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryMDto;
import com.spharosacademy.project.SSGBack.category.entity.CategoryProductList;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.repository.CategoryLRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategoryMRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategoryProductListRepository;
import com.spharosacademy.project.SSGBack.category.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.category.service.CategoryMService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryMServiceimple implements CategoryMService {

    private final CategoryMRepository categoryMRepository;
    private final CategoryLRepository categoryLRepository;
    private final CategorySRepository categorySRepository;
    private final CategoryProductListRepository categoryProductListRepository;


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
    public CategoryMDto getCategoryMById(Integer id) {

        CategoryM categoryM = categoryMRepository.findById(id).get();
        List<CategoryS> categorySList = categorySRepository.findAllByCategoryM(categoryM);
        List<CategoryProductList> productList = categoryProductListRepository.findAllByCategoryM(categoryM);

        List<CategorySofCategoryMDto> categorySofCategoryMDtoList = new ArrayList<>();

        for (CategoryS categoryS : categorySList) {
            categorySofCategoryMDtoList.add(CategorySofCategoryMDto.builder()
                    .id(categoryS.getId())
                    .name(categoryS.getName())
                    .build());
        }

        List<ProductOfCategory> productOfCategoryList = new ArrayList<>();
        for (CategoryProductList categoryProductList : productList) {
            productOfCategoryList.add(ProductOfCategory.builder()
                    .name(categoryProductList.getProduct().getName())
                    .id(categoryProductList.getProduct().getId())
                    .brand(categoryProductList.getProduct().getBrand())
                    .mallTxt(categoryProductList.getProduct().getMallText())
                    .price(categoryProductList.getProduct().getPrice())
                    .thumbnailUrl(categoryProductList.getProduct().getThumbnailUrl())
                    .build());
        }

        return CategoryMDto.builder()
                .name(categoryM.getName())
                .id(categoryM.getId())
                .categorySofCategoryMDtoList(categorySofCategoryMDtoList)
                .productOfCategoryList(productOfCategoryList)
                .build();
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

