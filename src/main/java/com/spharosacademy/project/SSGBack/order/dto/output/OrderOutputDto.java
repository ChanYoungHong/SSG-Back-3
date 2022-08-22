package com.spharosacademy.project.SSGBack.order.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderOutputDto {

    private Long orderId;
    private Long orderDetailId;
    private String userName;
    private String userAddress;
    private String productName;
    private String productThumbnailImageUrl;
    private String color;
    private String size;
    private float oldPrice;
    private float newPrice;
    private int qty;
}
