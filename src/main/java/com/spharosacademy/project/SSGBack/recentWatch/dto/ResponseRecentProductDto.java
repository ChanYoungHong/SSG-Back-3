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

    private Long productId;
    private String productName;
    private String productThumbnailImgUrl;
    private float price;
    private String mall;
    private String brand;
    private Long wishId;
}
