package com.spharosacademy.project.SSGBack.coupon.entity;

import com.spharosacademy.project.SSGBack.user.entity.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String couponName;

    private float discountRate;

    private Long discountAmount;

    private boolean expiredStatus;

    private String couponCondition;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User user;
}
