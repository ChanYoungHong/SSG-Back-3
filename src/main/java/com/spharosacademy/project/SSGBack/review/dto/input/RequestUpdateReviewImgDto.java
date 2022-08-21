package com.spharosacademy.project.SSGBack.review.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateReviewImgDto {
    private Long reviewImgId;
    private String reviewImgUrl;
    private String reviewImgTxt;
}
