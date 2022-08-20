package com.spharosacademy.project.SSGBack.order.dto.request;

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
public class OrdersOptioninputDto {

    private Long optionListId;
    private int qty;

}
