package com.spharosacademy.project.SSGBack.review.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserReviewDto {

    private Long reviewId;
    private Long productId;
    private String reviewTitle;
    private Long orderDetailId;
    private String productName;
    private String color;
    private String size;
    private String reviewContent;
    private float reviewScore;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String userLoginId;

    List<OutputReviewImgDto> outputReviewImgDtos;
}
