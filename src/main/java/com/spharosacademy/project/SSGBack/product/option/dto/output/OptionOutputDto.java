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

    private String color;
    private String size;
    private int stock;
}
