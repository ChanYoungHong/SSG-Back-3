package com.spharosacademy.project.SSGBack.product.option.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionInputDto {
    
    private String color;
    private String size;
    private int stock;
}
