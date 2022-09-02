package com.spharosacademy.project.SSGBack.product.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeAndStockDto {

    private Long id;
    private String name;
    private Long optionId;
    private int stock;
}
