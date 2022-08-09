package com.spharosacademy.project.SSGBack.review.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Data
public class ReviewResponseDto {

    private BigInteger reviewId;
    private String reviewContent;
    private String reviewAuthorId;
    private int reviewScore;
    private LocalDateTime reviewRegDate;

}
