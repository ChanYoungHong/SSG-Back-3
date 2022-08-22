package com.spharosacademy.project.SSGBack.category.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategorySofCategoryMDto {

    private int id;
    private String name;
}
