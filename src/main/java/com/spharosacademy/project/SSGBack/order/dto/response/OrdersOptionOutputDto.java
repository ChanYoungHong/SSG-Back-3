package com.spharosacademy.project.SSGBack.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
public class OrdersOptionOutputDto {

    private Long colorId;
    private Long sizeId;
    private int qty;

}
