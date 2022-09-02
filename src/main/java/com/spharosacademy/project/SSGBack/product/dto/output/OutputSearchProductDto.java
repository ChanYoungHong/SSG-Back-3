package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
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
    private float discountRate;
    private ReviewTotalDto reviewTotalDto;
    private String thumbnailImgUrl;
    private LocalDateTime regDate;
    private Long wishId;
}
