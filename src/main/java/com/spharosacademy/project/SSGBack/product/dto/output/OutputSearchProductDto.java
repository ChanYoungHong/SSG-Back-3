package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputSearchProductDto {

    private Long pid;
    private String name;
    private String brand;
    private String mall;
    private boolean inWish;
    private float nprice;
    private float oprice;
    private float drate;
    private ReviewTotalDto reviewTotalDto;
    private String thumbnailImgUrl;
    private LocalDateTime regDate;
}
