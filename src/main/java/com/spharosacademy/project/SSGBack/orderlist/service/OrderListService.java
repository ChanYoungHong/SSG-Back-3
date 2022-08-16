package com.spharosacademy.project.SSGBack.orderlist.service;

import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;

public interface OrderListService {

    OrderList purchase(OrderListInputDto inputDto);

//    // Request할 때.
//    default OrderList dtoToEntity(OrderListInputDto inputDto) {
//
//        OrderList entity = OrderList.builder()
//            .orderReceiver(inputDto.getMemberId().)
//            .userAddress()
//            .build();
//
//        return null;
//    }


}
