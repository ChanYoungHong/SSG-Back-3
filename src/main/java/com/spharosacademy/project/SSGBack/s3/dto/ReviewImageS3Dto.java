package com.spharosacademy.project.SSGBack.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewImageS3Dto {
    private String imageUrl;
    private String saveFileName;
}