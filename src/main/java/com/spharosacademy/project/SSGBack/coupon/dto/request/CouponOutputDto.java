package com.spharosacademy.project.SSGBack.coupon.dto.request;

import com.spharosacademy.project.SSGBack.coupon.Image.dto.CouponImageOutputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CouponOutputDto {

    private Long userId;
    private Long couponId;
    private String couponName;
    private float discountRate;
    private String couponCondition;
    private boolean expiredStatus;
    private LocalDate expiredDate;

    List<CouponImageOutputDto> couponImageOutputDtoList;
}
