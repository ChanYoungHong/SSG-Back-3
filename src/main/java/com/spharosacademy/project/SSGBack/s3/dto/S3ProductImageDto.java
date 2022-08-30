package com.spharosacademy.project.SSGBack.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class S3ProductImageDto {
    private String imageUrl;
    private String saveFileName;
}
