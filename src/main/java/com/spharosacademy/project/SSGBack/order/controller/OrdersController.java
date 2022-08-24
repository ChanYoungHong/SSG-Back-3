package com.spharosacademy.project.SSGBack.order.controller;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersUpdateDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersRemoveOutputDto;
import com.spharosacademy.project.SSGBack.order.exception.OutOfStockException;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
import java.util.List;

import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final JwtTokenProvider jwtTokenProvider;

    // 주문 등록
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void createDirectOrder(@RequestBody OrdersInputDto ordersInputDto) {
        ordersService.createDirectOrder(ordersInputDto);
    }

    // 회원번호로 주문목록 조회, 회원 아이디 아님
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdersOutputDto> checkMyOrder() {
        String token = jwtTokenProvider.customResolveToken();
        Long userid = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return ordersService.checkMyOrder(userid);
    }

    // 주문 이메일, 주소, 이름(받는 사람) 변경
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editMyOrderDetail(@RequestBody OrdersUpdateDto ordersUpdateDto) {
        ordersService.editMyOrderDetail(ordersUpdateDto);
    }

    // 주문 삭제
    @DeleteMapping("/remove/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMyOrder(@PathVariable Long orderId) {
        ordersService.removeMyOrderAndOrderList(orderId);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> handleOufOfStockException(OutOfStockException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
}
