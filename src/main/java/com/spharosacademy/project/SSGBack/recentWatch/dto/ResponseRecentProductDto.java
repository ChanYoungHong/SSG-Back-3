package com.spharosacademy.project.SSGBack.recentWatch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecentProductDto {

    private Long id;
    private String productName;
    private String thumbnailImgUrl;
    private float newPrice;
    private String mallTxt;
    private String productBrand;
    private Long wishId;
}
