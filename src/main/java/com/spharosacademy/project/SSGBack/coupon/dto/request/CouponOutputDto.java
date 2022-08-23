package com.spharosacademy.project.SSGBack.coupon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CouponOutputDto {

    private Long userId;
    private Long couponId;
    private String couponName;
    private Float discountRate;
    private String couponCondition;
    private boolean expiredStatus;

}
