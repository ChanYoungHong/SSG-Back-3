package com.spharosacademy.project.SSGBack.coupon.service;

import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponInputDto;
import com.spharosacademy.project.SSGBack.coupon.entity.Coupon;

public interface CouponService {

    Coupon addCoupon(CouponInputDto couponInputDto);
}
