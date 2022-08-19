package com.spharosacademy.project.SSGBack.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewOutputDto {

    private Long reviewId;
    private String reviewContent;
    private String reviewAuthorId;

    private Long productid;
    private String productName;
    private String filename;
    private String filepath;

    private Long optionId;
//    @Column(nullable = true)
    private Long colorId;

//    @Column(nullable = true)
    private Long sizeId;


    private Long userid;
    private int reviewScore;
    private Long count;
    private LocalDateTime createDate, updateDate;


}
