package com.spharosacademy.project.SSGBack.orderlist.controller;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderListController {

    @PostMapping
    public OrderListInputDto addOrderList(){

        return null;
    }


}
