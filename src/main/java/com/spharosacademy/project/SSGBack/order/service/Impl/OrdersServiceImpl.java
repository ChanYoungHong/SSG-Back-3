package com.spharosacademy.project.SSGBack.order.service.Impl;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersUpdateDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.exception.OrderIdNotFound;
import com.spharosacademy.project.SSGBack.order.exception.OrderedProductNotFound;
import com.spharosacademy.project.SSGBack.order.exception.OutOfStockException;
import com.spharosacademy.project.SSGBack.order.repo.OrdersRepository;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderListRepository orderListRepository;
    private final OptionRepository optionRepository;


    @Override
    public void createDirectOrder(OrdersInputDto ordersInputDto) {

        Optional<User> user = userRepository.findById(ordersInputDto.getMemberId());
        Product product = productRepository.findById(ordersInputDto.getProductId()).get();

        List<OrdersOptioninputDto> ordersOptioninputDtoList = new ArrayList<>();
        for (OrdersOptioninputDto ordersOptioninputDto : ordersInputDto.getOrdersOptioninputDtoList()) {
            ordersOptioninputDtoList.add(OrdersOptioninputDto.builder()
                    .optionListId(ordersOptioninputDto.getOptionListId())
                    .qty(ordersOptioninputDto.getQty())
                    .build());
        }
        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            if (ordersOptioninputDto.getQty() > optionRepository.findById(ordersOptioninputDto.getOptionListId()).get().getStock()) {
                throw new OutOfStockException();
            }
        });
        Orders order = ordersRepository.save(
                Orders.builder()
                        .user(user.get())
                        .OrderedDate(LocalDateTime.now())
                        .build()
        );
        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            OptionList optionList = optionRepository.findById(ordersOptioninputDto.getOptionListId()).get();
            OrderList orderList = orderListRepository.save(
                    OrderList.builder()
                            .orderAnOrderer(user.get().getUsername())
                            .optionId(optionRepository.findById(ordersOptioninputDto.getOptionListId()).get().getId())
                            .orderReceiver(user.get().getUsername())
                            .userAddress(user.get().getUserAddress())
                            .orderDecidedDate(LocalDateTime.now())
                            .product(product)
                            .orders(order)
                            .userPhoneNumber(user.get().getUserPhone())
                            .qty(ordersOptioninputDto.getQty())
                            .memberId(user.get().getId())
                            .orderMsg(ordersInputDto.getOrderMsg())
                            .userEmail(ordersInputDto.getUserEmail())
                            .build()
            );
            optionRepository.save(OptionList.builder()
                    .id(optionList.getId())
                    .colors(optionRepository.findById(orderList.getOptionId()).get().getColors())
                    .size(optionRepository.findById(orderList.getOptionId()).get().getSize())
                    .product(optionRepository.findById(orderList.getOptionId()).get().getProduct())
                    .stock(optionRepository.findById(orderList.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getStock()
                            - ordersOptioninputDto.getQty())
                    .build());
        });
    }


    @Override
    public List<OrdersOutputDto> checkMyOrder(Long memberId) {

        Optional<User> user = userRepository.findById(memberId);

        List<OrdersOutputDto> ordersOutputDtoList = new ArrayList<>();

        if (user.isPresent()) {
            List<OrderList> orderList =
                    orderListRepository.findAllByMemberId(user.get().getId());

            for (OrderList orderlist : orderList) {
                ordersOutputDtoList.add(OrdersOutputDto.builder()
                        .orderId(orderlist.getOrders().getOrderId())
                        .productId(orderlist.getProduct().getId())
                        .productName(orderlist.getProduct().getName())
                        .userName(user.get().getUsername())
                        .receiveAddress(user.get().getUserAddress())
                        .productPrice(orderlist.getProduct().getNewPrice())
                        .orderMsg(orderlist.getOrderMsg())
                        .userPhoneNumber(user.get().getUserPhone())
                        .qty(orderlist.getQty())
                        .build());
            }

        } else {
            throw new OrderedProductNotFound();
        }

        return ordersOutputDtoList;
    }

    @Override
    public void editMyOrderDetail(OrdersUpdateDto ordersUpdateDto) {

        User user = userRepository.findById(ordersUpdateDto.getUserId()).get();
        Product product = productRepository.findById(ordersUpdateDto.getProductId()).get();
        OrderList orderList = orderListRepository.findById(ordersUpdateDto.getOrderListId()).get();
        Orders orders = ordersRepository.findById(orderList.getOrders().getOrderId()).get();

        orderListRepository.save(
                OrderList.builder()
                        .orderReceiver(ordersUpdateDto.getOrderReceiver())
                        .orderAnOrderer(ordersUpdateDto.getOrderAnOrderer())
                        .memberId(user.getId())
                        .orderDecidedDate(orders.getOrderedDate())
                        .orderMsg(ordersUpdateDto.getOrderMsg())
                        .orderListId(ordersUpdateDto.getOrderListId())
                        .userEmail(ordersUpdateDto.getUserEmail())
                        .userAddress(ordersUpdateDto.getUserAddress())
                        .orders(orders)
                        .optionId(orderList.getOptionId())
                        .product(product)
                        .qty(orderList.getQty())
                        .userPhoneNumber(ordersUpdateDto.getUserPhoneNumber())
                        .build()
        );
//        for (OrdersUpdateDto ordersUpdateDto : ordersInputDto.getOrdersUpdateDtoList()) {
//            ordersUpdateDtoList.add(ordersUpdateDto.builder()
//                    .orderListId(ordersUpdateDto.getOrderListId())
//                    .userAddress(ordersUpdateDto.getUserAddress())
//                    .userEmail(ordersUpdateDto.getUserEmail())
//                    .orderMsg(ordersUpdateDto.getOrderMsg())
//                    .orderReceiver(ordersUpdateDto.getOrderReceiver())
//                    .build());
//        }
//
//        if (orderedUser.isPresent()) {
//            ordersUpdateDtoList.forEach(ordersUpdateDto -> {
//                orderListRepository.save(
//
//                        OrderList.builder()
//                                .orderListId(ordersUpdateDto.getOrderListId())
//                                .orderAnOrderer(user.getUsername())
//                                .optionId(orderedUser.get().getOptionId())
//                                .orderMsg(ordersUpdateDto.getOrderMsg())
//                                .orderReceiver(ordersUpdateDto.getOrderReceiver())
//                                .userAddress(ordersUpdateDto.getUserAddress())
//                                .orderDecidedDate(LocalDateTime.now())
//                                .product(product.get())
//                                .userEmail(ordersUpdateDto.getUserEmail())
//                                .userPhoneNumber(user.get().getUserPhone())
//                                .qty(orderedUser.get().getQty())
//                                .memberId(user.get().getId())
//                                .build()
//                );
//
//            });
//        }
    }

    @Override
    public void removeMyOrderAndOrderList(Long orderId) {

        Optional<Orders> orders = Optional.ofNullable(
                ordersRepository.findById(orderId).orElseThrow(OrderIdNotFound::new));

        if (orders.isPresent()) {

            List<OrderList> orderLists = orderListRepository.findAllByOrders(orders.get());

            for (OrderList orderList : orderLists) {
                orderListRepository.deleteById(orderList.getOrderListId());
            }
            ordersRepository.deleteById(orderId);
        }
    }

}





