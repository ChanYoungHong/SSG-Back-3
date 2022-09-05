package com.spharosacademy.project.SSGBack.wishlist.dto.output;

import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWishListDto {

    private Long wishId;
    private Long productId;
    private String productName;
    private String mallTxt;
    private String brand;
    private String thumbnailImgUrl;
    private float newPrice;
    private float oldPrice;
    private float discountRate;
    ReviewTotalDto reviewTotalDto;
    private String priceTxt;
}
