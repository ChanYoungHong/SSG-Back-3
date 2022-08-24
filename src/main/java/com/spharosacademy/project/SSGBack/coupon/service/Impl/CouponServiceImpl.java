package com.spharosacademy.project.SSGBack.coupon.service.Impl;

import com.spharosacademy.project.SSGBack.coupon.Image.dto.CouponImageOutputDto;
import com.spharosacademy.project.SSGBack.coupon.Image.entity.CouponImage;
import com.spharosacademy.project.SSGBack.coupon.Image.repository.CouponImageRepository;
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
    private final CouponImageRepository couponImageRepository;


    public void addCoupon(CouponInputDto couponInputDto) {
        User user = userRepository.findById(couponInputDto.getUserId()).get();

        Coupon coupon = couponRepository.save(Coupon.builder()
                .user(user)
                .discountRate(couponInputDto.getDiscountRate())
                .couponName(couponInputDto.getCouponName())
                .couponCondition(couponInputDto.getCouponCondition())
                .expiredStatus(couponInputDto.isExpiredStatus())
                .expiredDate(couponInputDto.getExpiredDate())
                .build());

        couponInputDto.getCouponImageInputDtoList().forEach
                (createImgDto -> couponImageRepository.save(
                        CouponImage.builder()
                                .coupon(coupon)
                                .couponImgTxt(createImgDto.getCouponImgTxt())
                                .couponImgUrl(createImgDto.getCouponImgUrl())
                                .build()));

    }

    @Override
    public List<CouponOutputDto> getCouponByUserId(Long userId) {
        List<Coupon> couponList = couponRepository.findAllByUserId(userId);
        List<CouponOutputDto> couponOutputDtos = new ArrayList<>();

        couponList.forEach(
                coupon -> {
                    List<CouponImageOutputDto> couponImageOutputDtos = new ArrayList<>();
                    List<CouponImage> couponImageList = couponImageRepository.findAllByCouponId(coupon.getId());
                    for (CouponImage couponImage : couponImageList) {
                        couponImageOutputDtos.add(CouponImageOutputDto.builder()
                                .couponImgUrl(couponImage.getCouponImgUrl())
                                .couponImgTxt(couponImage.getCouponImgTxt())
                                .build());
                    }

                    couponOutputDtos.add(CouponOutputDto.builder()
                            .couponId(coupon.getId())
                            .userId(coupon.getUser().getId())
                            .couponName(coupon.getCouponName())
                            .discountRate(coupon.getDiscountRate())
                            .couponCondition(coupon.getCouponCondition())
                            .expiredDate(coupon.getExpiredDate())
                            .couponImageOutputDtoList(couponImageOutputDtos)
                            .build());
                });
        return couponOutputDtos;
    }

}
