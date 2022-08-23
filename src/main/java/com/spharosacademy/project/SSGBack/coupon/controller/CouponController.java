package com.spharosacademy.project.SSGBack.coupon.controller;


import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponInputDto;
import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponOutputDto;
import com.spharosacademy.project.SSGBack.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;


    @PostMapping("/add")
    public String addCoupon(@RequestBody CouponInputDto couponInputDto) {
        couponService.addCoupon(couponInputDto);
        return "쿠폰이 등록 되었습니다";
    }

    @GetMapping("/{userId}")
    public List<CouponOutputDto> couponOutputDtos(@PathVariable Long userId) {
        return couponService.getCouponByUserId(userId);
    }

}
