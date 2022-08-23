package com.spharosacademy.project.SSGBack.review.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateReviewDto {

    private Long reviewId;
    private Long memberId;
    private Long productId;
    private Long orderDetailId;
    private float reviewScore;
    private String reviewContent;
    List<RequestUpdateReviewImgDto> requestUpdateReviewImgDtos;
}
