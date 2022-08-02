package com.spharosacademy.project.SSGBack.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmallCategoryDto {

    private Long id;
    private String name;
    private int middleCategoryId;
}
