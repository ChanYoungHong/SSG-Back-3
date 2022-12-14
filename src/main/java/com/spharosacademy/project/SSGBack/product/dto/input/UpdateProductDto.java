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

    private Long productId;
    private String productName;
    private int price;
    private String productColor;
    private String productBrand;
    private int productCnt;
    private int categorySSId;
    private int categorySId;
    private int categoryMId;
    private int categoryLId;
}
