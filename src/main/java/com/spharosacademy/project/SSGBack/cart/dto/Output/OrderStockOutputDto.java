package com.spharosacademy.project.SSGBack.cart.dto.Output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStockOutputDto {

    private float stock;
    private Long colorId;
    private Long productId;
    private String color;
    private Long sizeId;
    private String size;
    private Long optionId;

}
