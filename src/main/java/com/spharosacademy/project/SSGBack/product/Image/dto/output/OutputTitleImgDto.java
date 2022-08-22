package com.spharosacademy.project.SSGBack.product.Image.dto.output;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputTitleImgDto {
    private String productTitleImgUrl;
    private String productTitleImgTxt;
}
