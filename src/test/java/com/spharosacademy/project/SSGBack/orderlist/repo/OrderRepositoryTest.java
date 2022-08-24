//package com.spharosacademy.project.SSGBack.orderlist.repo;
//
//import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
//import java.util.stream.IntStream;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class OrderRepositoryTest {
//
//    @Autowired
//    private OrderListRepository orderListRepository;
//
//    @Test
//    public void insertOrder(){
//
//        IntStream.rangeClosed(1, 300).forEach(i -> {
//
//            OrderList orderList = OrderList.builder()
//                .orderListId((long) i)
//                .orderAnOrderer("user" + i)
//                .orderMsg("경비실에 부탁해용~")
//                .build();
//
//            orderListRepository.save(orderList);
//        });
//
//    }
//}