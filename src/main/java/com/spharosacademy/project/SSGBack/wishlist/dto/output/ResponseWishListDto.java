package com.spharosacademy.project.SSGBack.wishlist.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWishListDto {

    private Long wishListId;
    private Long productId;
    private String productName;
    private String mallTxt;
    private String brand;
    private String thumbnailImgUrl;
    private float price;
    private String priceTxt;
    private int stock;
}
