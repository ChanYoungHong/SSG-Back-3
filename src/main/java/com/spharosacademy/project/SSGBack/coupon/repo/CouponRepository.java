package com.spharosacademy.project.SSGBack.coupon.repo;

import com.spharosacademy.project.SSGBack.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByUserId(Long userId);

}
