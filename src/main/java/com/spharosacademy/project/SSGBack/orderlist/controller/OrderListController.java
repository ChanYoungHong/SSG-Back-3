package com.spharosacademy.project.SSGBack.orderlist.controller;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderListController {

    private final OrderListService orderListService;

    @PostMapping("/{memberId}/purchase")
    @ResponseStatus(HttpStatus.OK)
    public OrderList purchase(@RequestBody OrderListInputDto orderListInputDto){

        return orderListService.purchase(orderListInputDto);
    }

}
