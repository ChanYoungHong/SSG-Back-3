package com.spharosacademy.project.SSGBack.coupon.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponInputDto {

    private String couponName;
    private float discountRate;
    public LocalDateTime regedAt, expiredAt;
    private Long discountAmount;
    private String couponCondition;
    private boolean expiredStatus;

}
