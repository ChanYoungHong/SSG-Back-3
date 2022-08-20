package com.spharosacademy.project.SSGBack.order.controller;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOptionOutputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.user.dto.response.UserOutputDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void createDirectOrder(@RequestBody OrdersInputDto ordersInputDto){
        ordersService.createDirectOrder(ordersInputDto);
    }


    @GetMapping("/check/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdersOutputDto> checkMyOrder(@PathVariable Long memberId){

        return ordersService.checkMyOrder(memberId);
    }

    @PutMapping("/edit/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void editMyOrderDetail(@PathVariable Long memberId,
                                  @RequestBody OrdersInputDto ordersInputDto){

        ordersService.editMyOrderDetail(memberId, ordersInputDto);
    }

}
