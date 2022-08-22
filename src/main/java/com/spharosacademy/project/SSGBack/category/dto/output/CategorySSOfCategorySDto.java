package com.spharosacademy.project.SSGBack.category.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorySSOfCategorySDto {
    private int id;
    private String name;
}
