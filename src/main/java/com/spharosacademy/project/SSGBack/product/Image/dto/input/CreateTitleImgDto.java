package com.spharosacademy.project.SSGBack.product.Image.dto.input;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTitleImgDto {

    private String titleImgUrl;
    private String titleImgTxt;
}
