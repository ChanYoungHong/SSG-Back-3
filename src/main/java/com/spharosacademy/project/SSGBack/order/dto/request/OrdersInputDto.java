package com.spharosacademy.project.SSGBack.order.dto.request;

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
public class OrdersInputDto {

    private Long memberId;
    private Long productId;
    private Long ordersId;
    private String orderMsg;
    List<OrdersOptioninputDto> ordersOptioninputDtoList;
    List<OrdersUpdateDto> ordersUpdateDtoList;
}
