package com.spharosacademy.project.SSGBack.review.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestReviewImageDto {

    private String reviewImgUrl;
    private String reviewImgTxt;
}
