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
    private Long userId;
    private Long productId;
    private Long ordersId;
    List<OrdersOptionDto> ordersOptionDtoList;

//    private Long optionId;
//    private Long qty;

}
