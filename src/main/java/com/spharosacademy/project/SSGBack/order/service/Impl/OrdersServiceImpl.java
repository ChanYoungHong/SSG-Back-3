package com.spharosacademy.project.SSGBack.order.service.Impl;

import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.ProductRepository;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.repo.OrdersRepository;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.tempoptionlist.OptionListRepository;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    public void createDirectOrder(OrdersInputDto ordersInputDto) {

        Optional<User> user = userRepository.findById(ordersInputDto.getMemberId());
        Optional<Product> product = productRepository.findById(ordersInputDto.getProductId());

        Orders order = ordersRepository.save(
            Orders.builder()
                .orderedDate(LocalDateTime.now())
                .user(user.get())
                .build()
        );

        List<OrdersOptioninputDto> ordersOptioninputDtoList = new ArrayList<>();

        for(OrdersOptioninputDto ordersOptioninputDto : ordersOptioninputDtoList) {
            ordersOptioninputDtoList.add(ordersOptioninputDto.builder()
                .colorId(ordersOptioninputDto.getColorId())
                .sizeId(ordersOptioninputDto.getSizeId())
                .qty(ordersOptioninputDto.getQty())
                .build());
        }

        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            orderListRepository.save(
                OrderList.builder()
                    .orderAnOrderer(user.get().getUserName())
                    .optionId(optionListRepository.findByColorIdAndsizeId(ordersOptioninputDto.getColorId(), ordersOptioninputDto.getSizeId()).getOptionListId())
                    .orderMsg("집 앞에 부탁해용")
                    .orderReceiver(user.get().getUserName())
                    .orderState(false)
                    .userAddress(user.get().getUserAddress())
                    .orderDecidedDate(LocalDateTime.now())
                    .user(user.get())
                    .product(product.get())
                    .orders(order)
                    .cart(null)
                    .build()
            );
        });
    }

    @Override
    public Optional<OrderList> checkMyOrder(Long memberId) {
        return Optional.empty();
    }

//    @Override
//    public Optional<OrderList> checkMyOrder(Long memberId) {
//
//        Optional<OrderList> orderList =
//            Optional.ofNullable(orderListRepository.findById(memberId)
//                .orElseThrow(OrderedProductNotFound::new));
//
////        List<OrdersOptionOutputDto> orderOptionOutputDtoList = new ArrayList<>();
////        for (OrdersOptionOutputDto ordersOptionOutputDto : ) {
////            ordersOptionDtoList.add(OrdersOptionDto.builder()
////                .colorId(ordersOptionDto.getColorId())
////                .sizeId(ordersOptionDto.getSizeId())
////                .qty(ordersOptionDto.getQty())
////                .build());
////        }
//
//        if(!orderList.isEmpty()){
//            return orderListRepository.findById(memberId);
//        }
//        return orderList;
//    }


}



