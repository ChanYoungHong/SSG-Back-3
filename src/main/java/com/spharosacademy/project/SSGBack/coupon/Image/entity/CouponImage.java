package com.spharosacademy.project.SSGBack.coupon.Image.entity;

import com.spharosacademy.project.SSGBack.coupon.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CouponImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponImgUrl;
    private String couponImgTxt;

    @ManyToOne
    Coupon coupon;
}
