package com.spharosacademy.project.SSGBack.orderlist.service.Impl;

import com.spharosacademy.project.SSGBack.orderlist.dto.request.OrderListInputDto;
import com.spharosacademy.project.SSGBack.orderlist.dto.response.OrderListOutputDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.exception.ProducIdNotFoundException;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.orderlist.service.OrderListService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.MemberIdNotfound;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {

    private final OrderListRepository orderListRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderList addOrderList(OrderListInputDto inputDto) {

        Optional<User> user = Optional.ofNullable(userRepository.findById(inputDto.getMemberId()).
            orElseThrow(MemberIdNotfound::new));
        Optional<Product> product =
            Optional.ofNullable(productRepository.findById(inputDto.getProductId()).
                orElseThrow(ProducIdNotFoundException::new));

//        if (user.isPresent() && product.isPresent()) {
//            return orderListRepository.save(
//                OrderList.builder(
//
//            );
//        }
//        return null;
        return null;
    }

    @Override
    public List<OrderList> getAll(OrderListOutputDto outputDto) {
        return orderListRepository.findAll();
    }


}
