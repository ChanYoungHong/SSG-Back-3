package com.spharosacademy.project.SSGBack.coupon.entity;

import com.spharosacademy.project.SSGBack.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String couponName;
    private float discountRate;
    private boolean expiredStatus;
    private String couponCondition;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiredDate;

    @ManyToOne
    private User user;
}
