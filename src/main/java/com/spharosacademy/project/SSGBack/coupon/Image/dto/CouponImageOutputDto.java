package com.spharosacademy.project.SSGBack.coupon.Image.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CouponImageOutputDto {

    private String couponImgUrl;
    private String couponImgTxt;
}
