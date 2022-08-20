package com.spharosacademy.project.SSGBack.order.dto.request;


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
public class OrdersUpdateDto {

    private Long orderListId;
    private String userEmail;
    private String orderReceiver;
    private String userAddress;
    private String orderMsg;

}
