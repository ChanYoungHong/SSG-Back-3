package com.spharosacademy.project.SSGBack.coupon.service;

import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponInputDto;
import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponOutputDto;

import java.util.List;

public interface CouponService {

    void addCoupon(CouponInputDto couponInputDto, Long userId);

    List<CouponOutputDto> getCouponByUserId(Long userId);

}