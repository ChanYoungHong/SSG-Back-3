package com.spharosacademy.project.SSGBack.cart.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartOrderRequestDto {

    private String orderMsg;
    private String receiveAddress;
    private String userEmail;
    private List<OrderOptionRequestDto> orderOptionRequestDtos;
}
