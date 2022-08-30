package com.spharosacademy.project.SSGBack.s3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDto {
    private Long id;
    private String filePath;
}
