package com.spharosacademy.project.SSGBack.product.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorySDto {

    private int id;
    private String name;
    private int CategoryMId;
}
