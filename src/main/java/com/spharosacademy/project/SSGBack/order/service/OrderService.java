package com.spharosacademy.project.SSGBack.order.service;

import com.spharosacademy.project.SSGBack.order.dto.input.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;

public interface OrderService {

    Orders createOrder(OrderInputDto orderInputDto);
}
