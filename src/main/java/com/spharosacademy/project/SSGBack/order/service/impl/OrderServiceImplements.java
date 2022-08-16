package com.spharosacademy.project.SSGBack.order.service.impl;

import com.spharosacademy.project.SSGBack.order.dto.input.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.order.repository.OrderRepository;
import com.spharosacademy.project.SSGBack.order.service.OrderService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderServiceImplements implements OrderService {

    private final OrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public Orders createOrder(OrderInputDto orderInputDto) {
        User user = userRepository.findById(orderInputDto.getUserId()).get();
        Product product = productRepository.findById(orderInputDto.getProductId()).get();
        Orders orders = orderRepository.save(Orders.builder()
                .user(user)
                .build());

        orderDetailRepository.save(OrderDetail.builder()
                .user(user)
                .product(product)
                .optionId(orderInputDto.getOptionId())
                .address(user.getAddress())
                .qty(orderInputDto.getQty())
                .orders(orders)
                .build());

        return orders;
    }
}
