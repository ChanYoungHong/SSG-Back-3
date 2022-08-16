package com.spharosacademy.project.SSGBack.orderlist.service.Impl;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.orderlist.service.OrderListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {

    private OrderListRepository orderListRepository;


    @Override
    public OrderList purchase(OrderListInputDto inputDto) {


        return orderListRepository.save(
            OrderList.builder()
                .orderListId(inputDto.getOrderId())
                .orderState(inputDto.)
                .orderMsg(inputDto.getMemberId().)


                .build()
        );
    }

}
