package com.spharosacademy.project.SSGBack.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrdersInputDto {

    private Long memberId;
    private Long userId;
    private Long productId;
    private Long optionId;
    private Long qty;

}
