package com.spharosacademy.project.SSGBack.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReviewDto {

    private Long reviewId;
    private String reviewContent;
    private String reviewAuthorId;
    private int reviewScore;
    private Long reviewCount;
    private LocalDateTime createDate, updateDate;


}
