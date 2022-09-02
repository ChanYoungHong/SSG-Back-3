package com.spharosacademy.project.SSGBack.review.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductReviewImageDto {

    private String reviewImgUrl;
}
