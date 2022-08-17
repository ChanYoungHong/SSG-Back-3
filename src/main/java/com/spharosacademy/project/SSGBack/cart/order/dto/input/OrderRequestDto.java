package com.spharosacademy.project.SSGBack.cart.order.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long userId;
    private List<CartOrderRequestDto> cartOrderRequestDtos;
}
