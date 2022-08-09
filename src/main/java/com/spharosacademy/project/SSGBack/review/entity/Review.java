package com.spharosacademy.project.SSGBack.review.entity;

import com.spharosacademy.project.SSGBack.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")

public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger reviewId;
    private String reviewContent;
    private String reviewAuthorId;
    private LocalDateTime reviewRegDate;
    private LocalDateTime reviewModDate;

    //별점 어떻게 구현하지?
    @Min(value = 1, message = "별점은 1이상 5이하 여야 합니다")
    @Max(value = 5, message = "별점은 1이상 5이하 여야 합니다")
    private int reviewScore;
    private BigInteger review_cnt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private com.spharosacademy.project.SSGBack.product.entity.Product product;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User user;


}
