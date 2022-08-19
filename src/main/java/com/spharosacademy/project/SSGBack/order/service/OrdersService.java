package com.spharosacademy.project.SSGBack.order.service;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import java.util.List;
import java.util.Optional;

public interface OrdersService {

    void createDirectOrder(OrdersInputDto ordersInputDto);
    List<OrdersOutputDto> checkMyOrder(Long memberId);


}
