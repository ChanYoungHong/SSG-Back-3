package com.spharosacademy.project.SSGBack.orderlist.service;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.dto.response.OrderListOutputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import java.util.List;

public interface OrderListService {

    OrderList addOrderList(OrderListInputDto inputDto);

    List<OrderList> getAll(OrderListOutputDto outputDto);

}
