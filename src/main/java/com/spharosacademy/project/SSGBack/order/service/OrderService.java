package com.spharosacademy.project.SSGBack.order.service;

import com.spharosacademy.project.SSGBack.order.dto.request.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;

public interface OrderService {

    Orders buyOrdering(OrderInputDto orderInputDto);

    default Orders dtoToEntity(OrderInputDto dto){

        Orders order = Orders.builder()
            .orderId(dto.getOrderId())
            .orderAnOrderer(String.valueOf(dto.getUserId()))
            .orderMsg(dto.)

            .build();

        return order;
    }
}
