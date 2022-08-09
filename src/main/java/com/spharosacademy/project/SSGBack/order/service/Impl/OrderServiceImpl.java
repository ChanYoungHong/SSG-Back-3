package com.spharosacademy.project.SSGBack.order.service.Impl;

import com.spharosacademy.project.SSGBack.order.dto.request.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.repo.OrderRepository;
import com.spharosacademy.project.SSGBack.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Orders buyOrdering(OrderInputDto orderInputDto) {

        return ;
    }


}
