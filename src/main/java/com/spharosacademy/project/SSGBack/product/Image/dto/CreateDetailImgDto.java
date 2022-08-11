package com.spharosacademy.project.SSGBack.product.Image.dto;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetailImgDto {

    private String detailImgUrl;
    private String detailImgTxt;
    private Long ProductId;
}
