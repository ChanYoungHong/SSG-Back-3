package com.spharosacademy.project.SSGBack.coupon.Image.repository;

import com.spharosacademy.project.SSGBack.coupon.Image.entity.CouponImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponImageRepository extends JpaRepository<CouponImage, Long> {
    List<CouponImage> findAllByCouponId(Long couponId);
}
