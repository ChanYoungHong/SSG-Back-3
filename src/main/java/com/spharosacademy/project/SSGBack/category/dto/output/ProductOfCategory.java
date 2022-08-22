package com.spharosacademy.project.SSGBack.category.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOfCategory {

    private Long id;
    private String name;
    private String brand;
    private String mallTxt;
    private String thumbnailUrl;
    private float price;
}
