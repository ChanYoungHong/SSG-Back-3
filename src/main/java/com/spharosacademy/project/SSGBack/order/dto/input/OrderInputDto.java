package com.spharosacademy.project.SSGBack.order.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInputDto {

    private Long userId;
    private Long productId;
    private Long optionId;
    private Long cartId;
    private float qty;
}
