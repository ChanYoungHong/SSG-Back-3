package com.spharosacademy.project.SSGBack.review.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private String reviewTitle;
    private String reviewContent;
    private float reviewScore;
    private LocalDateTime regDate;
    private String userLoginId;

    List<OutputReviewImgDto> outputReviewImgDtos;
}
