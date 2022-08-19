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

    private Long orderListId;
    private Long memberId;
    private Long productId;

//    List<OrdersOptionOutputDto> orderOptionOutputDtoList;
}