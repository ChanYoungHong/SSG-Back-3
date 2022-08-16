package com.spharosacademy.project.SSGBack.product.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecommendProductDto {

    private Long id;
    private String name;
    private String titleImgUrl;
    private String mallText;
    private String brand;
    private String priceText;
    private float price;
}
