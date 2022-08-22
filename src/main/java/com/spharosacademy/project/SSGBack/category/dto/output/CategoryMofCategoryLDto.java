package com.spharosacademy.project.SSGBack.category.dto.output;

import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryMofCategoryLDto {

    private int id;
    private String name;
}
