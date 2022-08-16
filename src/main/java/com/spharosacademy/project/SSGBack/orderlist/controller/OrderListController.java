package com.spharosacademy.project.SSGBack.orderlist.controller;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderListController {

    @PostMapping
    public OrderListInputDto purchase(@RequestBody OrderListInputDto orderListInputDto){



        return null;
    }


}
