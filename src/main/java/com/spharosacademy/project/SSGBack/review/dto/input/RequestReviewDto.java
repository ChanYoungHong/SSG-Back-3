package com.spharosacademy.project.SSGBack.review.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReviewDto {

    private Long orderDetailId;
    private String reviewContent;
    private float reviewScore;

    List<RequestReviewImageDto> requestReviewImageDtos;
}
