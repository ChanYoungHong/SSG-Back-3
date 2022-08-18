package com.spharosacademy.project.SSGBack.product.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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
    private float newPrice;
    private float oldPrice;
    private String thumbnailImgUrl;
    private LocalDateTime regDate;
}
