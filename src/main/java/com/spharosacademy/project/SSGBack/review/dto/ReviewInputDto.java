package com.spharosacademy.project.SSGBack.review.dto;


import com.spharosacademy.project.SSGBack.review.Image.entity.ReviewImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInputDto {

    private Long reviewId;
    private Long productid;
    private String reviewContent;
    private String reviewAuthorId;
    private int reviewScore;
    private String filename;
    private String filepath;
    private LocalDateTime createDate, updateDate;

    List<ReviewImage> reviewImageList;
}
