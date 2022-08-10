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

    private BigInteger reviewId;
    private String reviewContent;
    private String reviewAuthorId;
    private int reviewScore;
    private LocalDateTime reviewRegDate;
    private LocalDateTime reviewModDate;


}
