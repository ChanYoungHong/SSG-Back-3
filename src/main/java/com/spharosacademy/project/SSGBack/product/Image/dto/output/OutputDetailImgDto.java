package com.spharosacademy.project.SSGBack.product.Image.dto.output;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputDetailImgDto {
    private String productDetailImgUrl;
    private String productDetailImgTxt;
}
