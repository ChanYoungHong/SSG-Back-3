package com.spharosacademy.project.SSGBack.review.dto;


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

    private Long reviewId;
    private String reviewContent;
    private String reviewAuthorId;
    private int reviewScore;
    private String filename;
    private String filepath;
    private Long reviewCount;
    private LocalDateTime createDate, updateDate;

//    List<ReviewImage> reviewImageList;
}
