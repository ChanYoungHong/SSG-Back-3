package com.spharosacademy.project.SSGBack.product.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDto {
    private Long ProductId;
    private String productName;
    private int price;
    private String productColor;
    private String productBrand;
    private int productCnt;
    private int CategorySSId;
}
