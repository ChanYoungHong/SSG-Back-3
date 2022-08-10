package com.spharosacademy.project.SSGBack.review.dto;

import com.spharosacademy.project.SSGBack.review.Image.entity.ReviewImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestReviewDto {

    private BigInteger reviewId;
    private String reviewContent;
    private String reviewAuthorId;
    private int reviewScore;
    private LocalDateTime reviewRegDate;

    List<ReviewImage> reviewImageList;
}
