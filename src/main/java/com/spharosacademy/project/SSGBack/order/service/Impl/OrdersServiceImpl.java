package com.spharosacademy.project.SSGBack.order.service.Impl;

import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.ProductRepository;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersInputDto;
import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptioninputDto;
import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.exception.OrderedProductNotFound;
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
    private final OptionListRepository optionListRepository;


    @Override
    public void createDirectOrder(OrdersInputDto ordersInputDto) {

        Optional<User> user = userRepository.findById(ordersInputDto.getMemberId());
        Optional<Product> product = productRepository.findById(ordersInputDto.getProductId());

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
                .qty(ordersOptioninputDto.getQty())
                .build());
            log.info("List 벗겨서 for문 돌리기");
        }

        ordersOptioninputDtoList.forEach(ordersOptioninputDto -> {
            log.info("오더인풋디티오에 들어와서 for문이 도나?");
            orderListRepository.save(

                OrderList.builder()
                    .orderAnOrderer(user.get().getUserName())
                    .optionId(ordersOptioninputDto.getOptionListId())
                    .orderMsg("집 앞에 부탁해용")
                    .orderReceiver(user.get().getUserName())
                    .userAddress(user.get().getUserAddress())
                    .orderDecidedDate(LocalDateTime.now())
                    .product(product.get())
                    .orders(order)
                    .memberId(user.get().getMemberId())
                    .build()
            );
            log.info("save가 되는지 확인하는 로그");
        });
    }


    @Override
    public List<OrdersOutputDto> checkMyOrder(Long memberId) {

        Optional<User> user = userRepository.findById(memberId);

        List<OrdersOutputDto> ordersOutputDtoList = null;

        if (user.isPresent()) {
            List<OrderList> orderList =
                (List<OrderList>) Optional.ofNullable(
                        orderListRepository.findAllByMemberId(user.get().getMemberId()))
                    .orElseThrow(OrderedProductNotFound::new);
            ordersOutputDtoList = new ArrayList<>();

            for (OrderList orderlist : orderList) {
                ordersOutputDtoList.add(OrdersOutputDto.builder()
                    .orderListId(orderlist.getOrderListId())
                    .productName(orderlist.getProduct().getName())
                    .userName(user.get().getUserName())
                    .userAddress(user.get().getUserAddress())
                    .productPrice(orderlist.getProduct().getPrice())
                    .orderMsg(orderlist.getOrderMsg())
                    .userPhoneNumber(user.get().getUserPhone())
                    .qty(orderlist.getQty())
                    .memberId(user.get().getMemberId())
                    .productId(orderlist.getProduct().getId())
                    .build());
            }
        }

        return ordersOutputDtoList;
    }
}





