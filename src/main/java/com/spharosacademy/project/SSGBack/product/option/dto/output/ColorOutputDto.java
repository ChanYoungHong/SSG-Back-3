package com.spharosacademy.project.SSGBack.product.option.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColorOutputDto {
    private Long colorId;
    private String color;
}
