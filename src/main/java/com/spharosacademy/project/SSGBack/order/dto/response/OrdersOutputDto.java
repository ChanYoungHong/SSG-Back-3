package com.spharosacademy.project.SSGBack.order.dto.response;


import java.util.List;
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

    private Long orderListId; // 주문 완료 리스트 정보
    private Long memberId; // 사용자 정보
    private Long productId; // 제품 정보
    private String userName;
    private String userAddress;
    private String userPhoneNumber;
    private String orderMsg;
    private String productName;
    private Long productPrice;
    private Long qty;

//    List<OrdersOptionOutputDto> orderOptionOutputDtoList;
}