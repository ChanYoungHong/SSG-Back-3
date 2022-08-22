package com.spharosacademy.project.SSGBack.orderlist.dto.request;

import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListInputDto { // 가격, 회사, 수량, 회원 아이디, 제품 번호, 색상, 사이즈, 사용자 주소, 제품 이름

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

//    private List<OrderInfoDto> orderInfo;

}
