package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.order.dto.output.OrderOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service

public class IUserServiceimple implements IUserService {

    private final IUserRepository iUserRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public User addUser(User user) {
        log.info("{} added", user);
        return iUserRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return iUserRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User editUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll User");
        return iUserRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public List<OrderOutputDto> getOrderList(Long userId) {

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByUserId(userId);
        List<OrderOutputDto> outputDtos = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            outputDtos.add(OrderOutputDto.builder()
                    .orderId(orderDetail.getOrders().getId())
                    .userName(iUserRepository.findById(userId)
                            .orElseThrow(UserNotFoundException::new).getName())
                    .userAddress(iUserRepository.findById(userId).
                            orElseThrow(UserNotFoundException::new).getAddress())
                    .productName(productRepository.findById(orderDetail.getProduct().getId())
                            .orElseThrow(ProductNotFoundException::new).getName())
                    .productThumbnailImageUrl(productRepository.findById(orderDetail.getProduct().getId())
                            .orElseThrow(ProductNotFoundException::new).getThumbnailUrl())
                    .oldPrice(productRepository.findById(orderDetail.getProduct().getId())
                            .orElseThrow(ProductNotFoundException::new).getOldPrice())
                    .newPrice(productRepository.findById(orderDetail.getProduct().getId())
                            .orElseThrow(ProductNotFoundException::new).getNewPrice())
                    .qty(orderDetail.getQty())
                    .build());
        }

        return outputDtos;
    }
}
