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
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
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
    public void createDirectOrder(OrdersInputDto ordersInputDto, Long userId) {

        Optional<User> user = userRepository.findById(userId);
        Product product = productRepository.findById(ordersInputDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        List<OrdersOptioninputDto> ordersOptioninputDtoList = new ArrayList<>();
        for (OrdersOptioninputDto ordersOptioninputDto : ordersInputDto.getOrdersOptioninputDtoList()) {
            ordersOptioninputDtoList.add(OrdersOptioninputDto.builder()
                    .optionListId(ordersOptioninputDto.getOptionListId())
                    .qty(ordersOptioninputDto.getQty())
                    .build());
        }
        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            if (ordersOptioninputDto.getQty() >
                    optionRepository.findById(ordersOptioninputDto.getOptionListId())
                            .orElseThrow(OptionNotFoundException::new)
                            .getStock()) {
                throw new OutOfStockException();
            }
        });
        Orders order = ordersRepository.save(
                Orders.builder()
                        .user(user.orElseThrow(UserNotFoundException::new))
                        .OrderedDate(LocalDateTime.now())
                        .build()
        );
        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            OptionList optionList =
                    optionRepository.findById(ordersOptioninputDto.getOptionListId())
                            .orElseThrow(OptionNotFoundException::new);
            OrderList orderList = orderListRepository.save(
                    OrderList.builder()
                            .orderAnOrderer(user.get().getUsername())
                            .optionId(optionRepository.findById(ordersOptioninputDto.getOptionListId())
                                    .orElseThrow(OptionNotFoundException::new)
                                    .getId())
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
                    .colors(optionRepository.findById(orderList.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getColors())
                    .size(optionRepository.findById(orderList.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getSize())
                    .product(optionRepository.findById(orderList.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getProduct())
                    .stock(optionRepository.findById(orderList.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getStock()
                            - ordersOptioninputDto.getQty())
                    .build());
        });
    }


    @Override
    public List<OrdersOutputDto> checkMyOrder(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        List<OrdersOutputDto> ordersOutputDtoList = new ArrayList<>();

        if (user.isPresent()) {
            List<OrderList> orderList = orderListRepository.findAllByMemberId(user.get().getId());

            for (OrderList orderlist : orderList) {
                ordersOutputDtoList.add(OrdersOutputDto.builder()
                        .orderId(orderlist.getOrders().getOrderId())
                        .orderListId(orderlist.getOrderId())
                        .productId(orderlist.getProduct().getId())
                        .productName(orderlist.getProduct().getName())
                        .userName(user.get().getUsername())
                        .orderRegDate(orderlist.getCreatedAt())
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
    public void editMyOrderDetail(OrdersUpdateDto ordersUpdateDto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(ordersUpdateDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        OrderList orderList = orderListRepository.findById(ordersUpdateDto.getOrderListId()).get();
        Orders orders = ordersRepository.findById(orderList.getOrders().getOrderId()).get();

        orderListRepository.save(
                OrderList.builder()
                        .orderReceiver(ordersUpdateDto.getOrderReceiver())
                        .orderAnOrderer(ordersUpdateDto.getOrderAnOrderer())
                        .memberId(user.getId())
                        .orderDecidedDate(orders.getOrderedDate())
                        .orderMsg(ordersUpdateDto.getOrderMsg())
                        .orderId(ordersUpdateDto.getOrderListId())
                        .userEmail(ordersUpdateDto.getUserEmail())
                        .userAddress(ordersUpdateDto.getUserAddress())
                        .orders(orders)
                        .optionId(orderList.getOptionId())
                        .product(product)
                        .qty(orderList.getQty())
                        .userPhoneNumber(ordersUpdateDto.getUserPhoneNumber())
                        .build()
        );
    }

    @Override
    public void removeMyOrderAndOrderList(Long orderId) {

        Optional<Orders> orders = Optional.ofNullable(
                ordersRepository.findById(orderId).orElseThrow(OrderIdNotFound::new));

        if (orders.isPresent()) {

            List<OrderList> orderLists = orderListRepository.findAllByOrders(orders.get());

            for (OrderList orderList : orderLists) {
                orderListRepository.deleteById(orderList.getOrderId());
            }
            ordersRepository.deleteById(orderId);
        }
    }

}





