package com.spharosacademy.project.SSGBack.order.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersOutputDto {

    //    private Long orderListId; // 주문 완료 리스트정보
//    private Long memberId; // 사용자 정보
    private Long orderId;
    private Long productId; // 제품 정보
    private String userName;
    private String userAddress;
    private String userPhoneNumber;
    private String orderMsg;
    private String productName;
    private float productPrice;
    private int qty;

//    List<OrdersOptionOutputDto> orderOptionOutputDtoList;
}