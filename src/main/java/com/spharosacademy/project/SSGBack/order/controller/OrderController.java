package com.spharosacademy.project.SSGBack.order.controller;

import com.spharosacademy.project.SSGBack.order.dto.input.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Orders createOrder(@RequestBody OrderInputDto orderInputDto){
           return orderService.createOrder(orderInputDto);
    }
}
