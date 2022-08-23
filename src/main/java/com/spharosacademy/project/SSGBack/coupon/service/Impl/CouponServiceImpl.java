package com.spharosacademy.project.SSGBack.coupon.service.Impl;

import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponInputDto;
import com.spharosacademy.project.SSGBack.coupon.dto.request.CouponOutputDto;
import com.spharosacademy.project.SSGBack.coupon.entity.Coupon;
import com.spharosacademy.project.SSGBack.coupon.repo.CouponRepository;
import com.spharosacademy.project.SSGBack.coupon.service.CouponService;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;


    @Override
    public void addCoupon(CouponInputDto couponInputDto) {
        User user = userRepository.findById(couponInputDto.getUserId()).get();
        couponRepository.save(Coupon.builder()
                .user(user)
                .couponName(couponInputDto.getCouponName())
                .discountRate(couponInputDto.getDiscountRate())
                .couponCondition(couponInputDto.getCouponCondition())
                .expiredStatus(couponInputDto.isExpiredStatus())
                .build());
    }

    @Override
    public List<CouponOutputDto> getCouponByUserId(Long userId) {
        List<Coupon> couponList = couponRepository.findAllByUserId(userId);
        List<CouponOutputDto> couponOutputDtos = new ArrayList<>();
        couponList.forEach(coupon -> {
            couponOutputDtos.add(CouponOutputDto.builder()
                    .couponId(coupon.getId())
                    .userId(coupon.getUser().getId())
                    .couponName(coupon.getCouponName())
                    .discountRate(coupon.getDiscountRate())
                    .couponCondition(coupon.getCouponCondition())
                    .build());
        });
            return couponOutputDtos;
        }

}
