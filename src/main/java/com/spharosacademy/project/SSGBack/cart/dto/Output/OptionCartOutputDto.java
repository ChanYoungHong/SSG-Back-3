package com.spharosacademy.project.SSGBack.cart.dto.Output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionCartOutputDto {

    private String color;
    private String size;
}
