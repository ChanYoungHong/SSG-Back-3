package com.spharosacademy.project.SSGBack.orderlist.controller;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.dto.response.OrderListOutputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.orderlist.service.OrderListService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class ProductListController {

    private final OrderListService orderListService;

    @PostMapping("/add")
    public OrderList addOrderList(@RequestBody OrderListInputDto orderListInputDto){
        return orderListService.addOrderList(orderListInputDto);
    }



}
