package com.spharosacademy.project.SSGBack.product.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputSearchProductDto {

    private Long id;
    private String name;
    private String brand;
    private String mallTxt;
    private String priceTxt;
    private int price;
    private String thumbnailImgUrl;
}
