package com.spharosacademy.project.SSGBack.orderlist.service;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;

public interface OrderListService {

    OrderList buyOrdering(OrderListInputDto orderInputDto);

    default OrderList dtoToEntity(OrderListInputDto dto){

        OrderList order = OrderList.builder()
            .orderId(dto.getOrderId())
            .orderAnOrderer(String.valueOf(dto.getUserId()))

            .build();

        return order;
    }
}
