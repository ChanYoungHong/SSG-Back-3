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
public class ResponseProductReviewDto {

    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private String userLoginId;
    private Long orderDetailId;
    private LocalDateTime regDate;
    private float reviewScore;
    private String color;
    private String size;
    private LocalDateTime updateDate;
    List<OutputReviewImgDto> outputReviewImgDtos;
}
