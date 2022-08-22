package com.spharosacademy.project.SSGBack.cart.service.Impl;

import com.spharosacademy.project.SSGBack.cart.dto.Output.OptionCartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.Output.OrderStockOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.*;
import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.repository.CartRepository;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.order.exception.OutOfStockException;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.order.repository.OrderRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceimple implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    @Override
    public Cart addProductToCart(CartInputDto cartInputDto) {
        //상품의 존재 여부를 판단한다
        Product product = productRepository.findById(cartInputDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        User user = userRepository.findById(cartInputDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        List<CartOptionDto> cartOptionDtos = new ArrayList<>();
        Long duplicate;
        for (CartOptionDto cartOptionDto : cartInputDto.getCartOptionDtos()) {
            cartOptionDtos.add(CartOptionDto.builder()
                    .optionId(cartOptionDto.getOptionId())
                    .qty(cartOptionDto.getQty())
                    .build());

            duplicate = cartRepository.findByUserIdAndOptionId(user.getMemberId(), cartOptionDto.getOptionId());

            if (duplicate == null) {

                cartRepository.save(Cart.builder()
                        .product(product)
                        .user(user)
                        .optionId(cartOptionDto.getOptionId())
                        .qty(cartOptionDto.getQty())
                        .sizeId(optionRepository.findById(cartOptionDto.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getSize().getId())
                        .colorId(optionRepository.findById(cartOptionDto.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getColors().getId())
                        .build());

            } else {
                cartRepository.save(Cart.builder()
                        .user(user)
                        .product(product)
                        .id(duplicate)
                        .sizeId(optionRepository.findById(cartOptionDto.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getSize().getId())
                        .optionId(cartRepository.findById(cartOptionDto.getOptionId()).get().getOptionId())
                        .qty(cartOptionDto.getQty() + cartRepository.findById(duplicate).get().getQty())
                        .colorId(optionRepository.findById(cartOptionDto.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getColors().getId())
                        .build());
            }

        }

        return null;
    }

    @Override
    public List<OrderStockOutputDto> orderCart(CartOrderRequestDto cartOrderRequestDto) {
        User user = userRepository.findById(cartOrderRequestDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        List<OrderOptionRequestDto> orderOptionRequestDtos = new ArrayList<>();
        for (OrderOptionRequestDto orderOptionRequestDto : cartOrderRequestDto.getOrderOptionRequestDtos()) {
            orderOptionRequestDtos.add(OrderOptionRequestDto.builder()
                    .cartId(orderOptionRequestDto.getCartId())
                    .qty(orderOptionRequestDto.getQty())
                    .build());

        }
        orderOptionRequestDtos.forEach(orderOptionRequestDto -> {
            if (orderOptionRequestDto.getQty() > optionRepository.findById(orderOptionRequestDto
                    .getCartId()).get().getStock()) {
                throw new OutOfStockException();
            }
        });
        Orders orders = orderRepository.save(Orders.builder()
                .user(user)
                .build());

        orderOptionRequestDtos.forEach(orderOptionRequestDto -> {
            Cart cart = cartRepository.findById(orderOptionRequestDto.getCartId()).get();
            Product product = productRepository.findById(cart.getProduct().getId()).get();
            OrderDetail orderDetail = orderDetailRepository.save(OrderDetail.builder()
                    .address(user.getUserAddress())
                    .userId(user.getMemberId())
                    .qty(orderOptionRequestDto.getQty())
                    .optionId(cartRepository.findById(orderOptionRequestDto.getCartId())
                            .get().getOptionId())
                    .totalPrice(orderOptionRequestDto.getQty() * product.getNewPrice())
                    .orders(orders)
                    .product(cartRepository.findById(orderOptionRequestDto.getCartId())
                            .get().getProduct())
                    .build());

            optionRepository.save(OptionList.builder()
                    .id(orderDetail.getOptionId())
                    .colors(optionRepository.findById(orderDetail.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getColors())
                    .size(optionRepository.findById(orderDetail.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getSize())
                    .product(optionRepository.findById(orderDetail.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getProduct())
                    .stock(optionRepository.findById(orderDetail.getOptionId())
                            .orElseThrow(OptionNotFoundException::new).getStock()
                            - orderDetail.getQty())
                    .build());
        });


        for (OrderOptionRequestDto orderOptionRequestDto : orderOptionRequestDtos) {
            cartRepository.deleteById(orderOptionRequestDto.getCartId());
        }
        return null;


    }

    @Override
    public void updateCart(CartUpdateRequestDto cartUpdateRequestDto) {
        Cart cart = cartRepository.findById(cartUpdateRequestDto.getCartId())
                .orElseThrow(ProductNotFoundException::new);

        cartRepository.save(Cart.builder()
                .id(cartUpdateRequestDto.getCartId())
                .colorId(cartUpdateRequestDto.getColorId())
                .sizeId(cartUpdateRequestDto.getSizeId())
                .optionId(optionRepository.findByColors_IdAndSize_Id(cartUpdateRequestDto.getColorId(),
                        cartUpdateRequestDto.getSizeId()).getId())
                .user(cart.getUser())
                .product(cart.getProduct())
                .qty(cart.getQty())
                .build());
    }

    @Override
    public List<OptionList> getOptionByProduct(Long productId) {
        return optionRepository.findByProductId(productId);
    }

    @Override
    public List<CartOutputDto> getAllCart() {
        List<Cart> ListCart = cartRepository.findAll();
        List<CartOutputDto> cartOutputDtoList = new ArrayList<>();
        ListCart.forEach(cart -> cartOutputDtoList.add(CartOutputDto.builder()
                .id(cart.getId())
                .productid(cart.getProduct().getId())
                .productName(cart.getProduct().getName())
                .titleImgUrl(cart.getProduct().getThumbnailUrl())
                .useraddress(cart.getUser().getUserAddress())
                .username(cart.getUser().getUsername())
                .productBrand(cart.getProduct().getBrand())
                .price(cart.getProduct().getNewPrice())
                .optionCartOutputDto(OptionCartOutputDto.builder()
                        .color(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getColors().getName())
                        .size(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getSize().getType())
                        .build())
                .qty(cart.getQty())
                .build()));
        return cartOutputDtoList;
    }

    @Override
    public List<CartOutputDto> getCartByUserId(Long userid) {
        List<Cart> carts = cartRepository.findByUserId(userid);
        List<CartOutputDto> outputDtos = new ArrayList<>();

        for (Cart cart : carts) {
            outputDtos.add(CartOutputDto.builder()
                    .id(cart.getId())
                    .productid(cart.getProduct().getId())
                    .productName(cart.getProduct().getName())
                    .titleImgUrl(cart.getProduct().getThumbnailUrl())
                    .useraddress(cart.getUser().getUserAddress())
                    .username(cart.getUser().getUsername())
                    .productBrand(cart.getProduct().getBrand())
                    .price(cart.getProduct().getNewPrice())
                    .qty(cart.getQty())
                    .optionCartOutputDto(OptionCartOutputDto.builder()
                            .color(optionRepository.findById(cart.getOptionId())
                                    .orElseThrow(OptionNotFoundException::new).getColors().getName())
                            .size(optionRepository.findById(cart.getOptionId())
                                    .orElseThrow(OptionNotFoundException::new).getSize().getType())
                            .build())
                    .count(cartRepository.countByUserId(userid))
                    .build());
        }
        return outputDtos;
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

}


