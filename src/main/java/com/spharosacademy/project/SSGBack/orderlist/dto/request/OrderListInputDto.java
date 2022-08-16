package com.spharosacademy.project.SSGBack.orderlist.dto.request;

import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListInputDto { // 환불 여부도 생각하기,

    private Long memberId;
    private Long orderId;
    private Long productId;
    private Long cartId;

    private String size;
    private String color;
    private String userAddress;

}
