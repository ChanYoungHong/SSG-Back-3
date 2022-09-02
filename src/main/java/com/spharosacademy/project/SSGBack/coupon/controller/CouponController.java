package com.spharosacademy.project.SSGBack.coupon.controller;


import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponInputDto;
import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponOutputDto;
import com.spharosacademy.project.SSGBack.coupon.service.CouponService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/coupon")
@RequiredArgsConstructor
@CrossOrigin
public class CouponController {

    private final CouponService couponService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/add")
    public String addCoupon(@RequestBody CouponInputDto couponInputDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        couponService.addCoupon(couponInputDto, userId);
        return "쿠폰이 등록 되었습니다";
    }

    @GetMapping("/getByUserId")
    public List<CouponOutputDto> couponOutputDtos() {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return couponService.getCouponByUserId(userId);
    }

}
