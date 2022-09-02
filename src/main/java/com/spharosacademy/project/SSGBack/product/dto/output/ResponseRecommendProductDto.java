package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecommendProductDto {

    private Long id;
    private String name;
    private String titleImgUrl;
    private String mallText;
    private String brand;
    private String priceText;
    private Long wishId;
    private float oldPrice;
    private float newPrice;
    private float discountRate;
    ReviewTotalDto reviewTotalDto;
    private LocalDateTime regDate;
}
