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
public class OrdersRemoveOutputDto {

    private Long orderListId;
    private Long orderId;

}
