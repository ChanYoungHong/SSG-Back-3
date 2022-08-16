package com.spharosacademy.project.SSGBack.orderlist.service.Impl;

import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.ProductRepository;
import com.spharosacademy.project.SSGBack.order.repo.OrderRepository;
import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.orderlist.service.OrderListService;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {

    private OrderListRepository orderListRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;



    @Override
    public OrderList purchase(OrderListInputDto inputDto) {

        Optional<User> result1 = userRepository.findById(inputDto.getMemberId());
        Optional<Product> result2 = productRepository.findById(inputDto.getProductId());

        return orderListRepository.save(
            OrderList.builder()
                .orderListId(result1.get().getMemberId())
                .orderAnOrderer(result1.get().getUserName())
                .userAddress(result1.get().getUserAddress())
                .orderReceiver(result1.get().getUserName())
                .orderMsg("직접 받겠습니다")
                .optionId(Long.valueOf(result2.get().getColor()))
                .optionId(Long.valueOf(result2.get().getSize()))
                .build()
        );
    }

}
