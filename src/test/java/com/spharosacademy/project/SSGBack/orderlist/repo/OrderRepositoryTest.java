package com.spharosacademy.project.SSGBack.orderlist.repo;

import com.spharosacademy.project.SSGBack.orderlist.entity.Orders;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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