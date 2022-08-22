package com.spharosacademy.project.SSGBack.cart.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderOptionRequestDto {

    private Long cartId;
    private float qty;
}
