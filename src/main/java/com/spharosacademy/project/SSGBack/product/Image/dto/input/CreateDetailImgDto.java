package com.spharosacademy.project.SSGBack.product.Image.dto.input;

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
}
