package com.spharosacademy.project.SSGBack.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryMDto {

    private int id;
    private String name;
    private int CategoryLId;
}
