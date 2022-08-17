package com.spharosacademy.project.SSGBack.product.option.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionOutputDto {
    private Long id;
    private String color;
    private Long colorId;
    private String size;
    private Long sizeId;
    private int stock;
}
