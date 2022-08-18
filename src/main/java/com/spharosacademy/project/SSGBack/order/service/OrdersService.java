package com.spharosacademy.project.SSGBack.order.service;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptionDto;
import java.util.List;

public interface OrdersService {

    void createDirectOrder(OrdersInputDto ordersInputDto);

//    List<OrdersOptionDto>

}
