package com.spharosacademy.project.SSGBack.order.service.Impl;

import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.ProductRepository;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.repo.OrdersRepository;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.tempoptionlist.OptionList;
import com.spharosacademy.project.SSGBack.tempoptionlist.OptionListRepository;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderListRepository orderListRepository;
    private final OptionListRepository optionListRepository;


    @Override
    public Orders createDirectOrder(OrdersInputDto ordersInputDto) {

        Optional<User> user = userRepository.findById(ordersInputDto.getMemberId());
        Optional<OptionList> option = optionListRepository.findById(ordersInputDto.getOrdersId());
        Optional<Product> product = productRepository.findById(ordersInputDto.getProductId());
        Orders order = ordersRepository.save(
            Orders.builder()
                .orderedDate(LocalDateTime.now())
                .user(user.get())
                .build()
        );

        return orderListRepository.save(
            OrderList.builder()
                .orderAnOrderer(user.get().getUserName())
                .optionId() // 옵션은 어디서 들고오나?
                .build()
        );
    }


//    @Override
//    public OrderList purchase(OrderListInputDto inputDto) {
//
//        Optional<User> result1 = userRepository.findById(inputDto.getMemberId());
//        Optional<Product> result2 = productRepository.findById(inputDto.getProductId());
//
//        return orderListRepository.save(
//            OrderList.builder()
//                .orderListId(result1.get().getMemberId())
//                .orderAnOrderer(result1.get().getUserName())
//                .userAddress(result1.get().getUserAddress())
//                .orderReceiver(result1.get().getUserName())
//                .orderMsg("직접 받겠습니다")
//                .optionId(Long.valueOf(result2.get().getColor()))
//                .optionId(Long.valueOf(result2.get().getSize()))
//                .build()
//        );
//    }

}
