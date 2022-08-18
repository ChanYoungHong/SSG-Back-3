package com.spharosacademy.project.SSGBack.user.service;

import com.spharosacademy.project.SSGBack.order.dto.output.OrderOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
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
        return iUserRepository.findById(id).get();
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
        User user = iUserRepository.findById(userId).get();
        List<OrderDetail> detailList = orderDetailRepository.findByUserId(user.getId());
        List<OrderOutputDto> outputDtos = new ArrayList<>();
        for (OrderDetail orderDetail : detailList) {
            outputDtos.add(OrderOutputDto.builder()
                    .orderId(orderDetail.getOrders().getId())
                    .userName(iUserRepository.findById(orderDetail.getUser().getId())
                            .get().getName())
                    .userAddress(iUserRepository.findById(orderDetail.getUser().getId())
                            .get().getAddress())
                    .productName(productRepository.findById(orderDetail.getProduct().getId()).get().getName())
                    .productThumbnailImageUrl(productRepository.findById(orderDetail.getProduct().getId()).get().getThumbnailUrl())
                    .build());
        }

        return outputDtos;
    }
}
