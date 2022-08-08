package com.spharosacademy.project.SSGBack.product.dto.input;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductDto {

    private String productName;
    private int price;
    private String productColor;
    private String productBrand;
    private int productCnt;
    private int CategorySSId;
    private String titleImgUrl;

}
