package com.spharosacademy.project.SSGBack.order.repo;

import static org.junit.jupiter.api.Assertions.*;

import com.spharosacademy.project.SSGBack.order.entity.Orders;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void insertOrder(){

        IntStream.rangeClosed(1, 300).forEach(i -> {

            Orders order = Orders.builder()
                .orderId((long) i)
                .orderAnOrderer("user" + i)
                .orderMsg("경비실에 부탁해용~")
                .build();

            orderRepository.save(order);
        });

    }
}