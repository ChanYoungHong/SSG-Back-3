package com.spharosacademy.project.SSGBack.cart.order.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartOrderRequestDto {

    private Long cartId;
    private float qty;
}
