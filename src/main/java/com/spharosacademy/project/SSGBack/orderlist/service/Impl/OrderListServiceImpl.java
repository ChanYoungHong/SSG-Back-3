package com.spharosacademy.project.SSGBack.orderlist.service.Impl;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderRepository;
import com.spharosacademy.project.SSGBack.orderlist.service.OrderListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {

    private final OrderRepository orderRepository;


    @Override
    public OrderList buyOrdering(OrderListInputDto orderInputDto) {
        return null;
    }

    @Override
    public OrderList dtoToEntity(OrderListInputDto dto) {
        return OrderListService.super.dtoToEntity(dto);
    }
}
