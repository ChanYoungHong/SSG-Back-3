package com.spharosacademy.project.SSGBack.cart.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartInputDto {
    private Long userId;
    private Long productId;
    private List<CartOptionDto> cartOptionDtos;
}
