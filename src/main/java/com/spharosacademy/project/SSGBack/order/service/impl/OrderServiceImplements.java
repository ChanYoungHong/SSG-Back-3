package com.spharosacademy.project.SSGBack.order.service.impl;

import com.spharosacademy.project.SSGBack.order.dto.input.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.order.repository.OrderRepository;
import com.spharosacademy.project.SSGBack.order.service.OrderService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
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
    private final OptionRepository optionRepository;

    @Override
    public Orders createOrder(OrderInputDto orderInputDto) {
        User user = userRepository.findById(orderInputDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(orderInputDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        OptionList optionList = optionRepository.findById(orderInputDto.getOptionId())
                .orElseThrow(OptionNotFoundException::new);
        Orders orders = orderRepository.save(Orders.builder()
                .user(user)
                .build());

        orderDetailRepository.save(OrderDetail.builder()
                .product(product)
                .optionId(optionList.getId())
                .address(user.getUserAddress())
                .qty(orderInputDto.getQty())
                .totalPrice(orderInputDto.getQty() * product.getNewPrice())
                .orders(orders)
                .build());

        return orders;
    }
}
