package com.spharosacademy.project.SSGBack.order.controller;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersRemoveOutputDto;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    // 주문 등록
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void createDirectOrder(@RequestBody OrdersInputDto ordersInputDto) {
        ordersService.createDirectOrder(ordersInputDto);
    }

    // 회원번호로 주문목록 조회, 회원 아이디 아님
    @GetMapping("/check/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdersOutputDto> checkMyOrder(@PathVariable Long memberId) {

        return ordersService.checkMyOrder(memberId);
    }

    // 주문 이메일, 주소, 이름(받는 사람) 변경
    @PutMapping("/edit/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void editMyOrderDetail(@PathVariable Long memberId,
                                  @RequestBody OrdersInputDto ordersInputDto) {

        ordersService.editMyOrderDetail(memberId, ordersInputDto);
    }

    // 주문 삭제
    @DeleteMapping("/remove/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMyOrder(@PathVariable Long orderId) {
        ordersService.removeMyOrderAndOrderList(orderId);
    }
}
