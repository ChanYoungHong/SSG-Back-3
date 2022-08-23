package com.spharosacademy.project.SSGBack.order.service.Impl;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersUpdateDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.exception.OrderIdNotFound;
import com.spharosacademy.project.SSGBack.order.exception.OrderedProductNotFound;
import com.spharosacademy.project.SSGBack.order.repo.OrdersRepository;
import com.spharosacademy.project.SSGBack.order.service.OrdersService;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
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


    @Override
    public void createDirectOrder(OrdersInputDto ordersInputDto) {

        Optional<User> user = userRepository.findById(ordersInputDto.getMemberId());
        Product product = productRepository.findById(ordersInputDto.getProductId()).get();
        Optional<OrderList> orderList = orderListRepository.findById(
                ordersInputDto.getOrdersOptioninputDtoList().get(0).getOptionListId());
        Orders order = ordersRepository.save(
                Orders.builder()
                        .user(user.get())
                        .OrderedDate(LocalDateTime.now())
                        .build()
        );

        List<OrdersOptioninputDto> ordersOptioninputDtoList = new ArrayList<>();

        for (OrdersOptioninputDto ordersOptioninputDto : ordersInputDto.getOrdersOptioninputDtoList()) {
            ordersOptioninputDtoList.add(ordersOptioninputDto.builder()
                    .optionListId(ordersOptioninputDto.getOptionListId())
                    .orderMsg(ordersOptioninputDto.getOrderMsg())
                    .qty(ordersOptioninputDto.getQty())
                    .build());
            log.info("List 벗겨서 for문 돌리기");
        }

        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            log.info("오더인풋디티오에 들어와서 for문이 도나?");
            orderListRepository.save(

                    OrderList.builder()
                            .orderAnOrderer(user.get().getUsername())
                            .optionId(ordersOptioninputDto.getOptionListId())
                            .orderMsg(ordersOptioninputDto.getOrderMsg())
                            .orderReceiver(user.get().getUsername())
                            .userAddress(user.get().getUserAddress())
                            .orderDecidedDate(LocalDateTime.now())
                            .product(product)
                            .orders(order)
                            .userPhoneNumber(user.get().getUserPhone())
                            .qty(ordersOptioninputDto.getQty())
                            .memberId(user.get().getId())
                            .build()
            );
            log.info("save가 되는지 확인하는 로그");
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
                        .userAddress(user.get().getUserAddress())
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
    public void editMyOrderDetail(Long memberId, OrdersInputDto ordersInputDto) {

        Optional<User> user = userRepository.findById(ordersInputDto.getMemberId());
        Optional<OrderList> orderedUser = orderListRepository.findById(
                ordersInputDto.getOrdersUpdateDtoList().get(0).getOrderListId());
        Optional<Product> product = productRepository.findById(ordersInputDto.getProductId());
        Optional<Orders> orders = ordersRepository.findById(ordersInputDto.getOrdersId());

        List<OrdersUpdateDto> ordersUpdateDtoList = new ArrayList<>();

        for (OrdersUpdateDto ordersUpdateDto : ordersInputDto.getOrdersUpdateDtoList()) {
            ordersUpdateDtoList.add(ordersUpdateDto.builder()
                    .orderListId(ordersUpdateDto.getOrderListId())
                    .userAddress(ordersUpdateDto.getUserAddress())
                    .userEmail(ordersUpdateDto.getUserEmail())
                    .orderMsg(ordersUpdateDto.getOrderMsg())
                    .orderReceiver(ordersUpdateDto.getOrderReceiver())
                    .build());
        }

        if (orderedUser.isPresent()) {
            ordersUpdateDtoList.forEach(ordersUpdateDto -> {
                orderListRepository.save(

                        OrderList.builder()
                                .orderListId(ordersUpdateDto.getOrderListId())
                                .orderAnOrderer(user.get().getUsername())
                                .optionId(orderedUser.get().getOptionId())
                                .orderMsg(ordersUpdateDto.getOrderMsg())
                                .orderReceiver(ordersUpdateDto.getOrderReceiver())
                                .userAddress(ordersUpdateDto.getUserAddress())
                                .orderDecidedDate(LocalDateTime.now())
                                .product(product.get())
                                .orders(orders.get())
                                .userEmail(ordersUpdateDto.getUserEmail())
                                .userPhoneNumber(user.get().getUserPhone())
                                .qty(orderedUser.get().getQty())
                                .memberId(user.get().getId())
                                .build()
                );

            });
        }
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





