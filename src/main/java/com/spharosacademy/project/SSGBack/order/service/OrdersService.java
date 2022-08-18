package com.spharosacademy.project.SSGBack.order.service;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptionDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import java.util.List;

public interface OrdersService {

    Orders createDirectOrder(OrdersInputDto ordersInputDto);

    List<OrdersOptionDto>
}
