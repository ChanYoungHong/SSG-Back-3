package com.spharosacademy.project.SSGBack.category.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMDto {

    private int id;
    private String name;
    List<CategorySofCategoryMDto> categorySofCategoryMDtoList;
    List<ProductOfCategory> productOfCategoryList;
}
