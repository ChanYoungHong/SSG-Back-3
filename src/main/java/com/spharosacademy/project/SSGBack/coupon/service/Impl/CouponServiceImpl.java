package com.spharosacademy.project.SSGBack.coupon.service.Impl;

import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponInputDto;
import com.spharosacademy.project.SSGBack.coupon.entity.Coupon;
import com.spharosacademy.project.SSGBack.coupon.repo.CouponRepository;
import com.spharosacademy.project.SSGBack.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public Coupon addCoupon(CouponInputDto couponInputDto) {

        return couponRepository.save();
    }

}
