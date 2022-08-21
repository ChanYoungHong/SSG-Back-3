package com.spharosacademy.project.SSGBack.orderlist.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderListOutputDto {

    private Long userId;
    private Long memberId;
    private Long productId;
    private String userAddress;
    private Long orderId;
    private Long price;
    private Long productCnt;
    private String productColor;
    private String productSize;
    private String productName;

}
