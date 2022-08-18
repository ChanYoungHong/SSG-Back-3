package com.spharosacademy.project.SSGBack.order.service;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import java.util.Optional;

public interface OrdersService {

    void createDirectOrder(OrdersInputDto ordersInputDto);
    Optional<OrderList> checkMyOrder(Long memberId);


}
