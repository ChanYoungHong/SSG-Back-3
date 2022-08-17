package com.spharosacademy.project.SSGBack.cart.service;

import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.order.dto.input.CartOrderRequestDto;
import com.spharosacademy.project.SSGBack.cart.order.dto.input.OrderRequestDto;
import com.spharosacademy.project.SSGBack.order.dto.input.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;

import java.util.List;

public interface CartService {

    Cart addProductToCart(CartInputDto cartInputDto);

    List<CartOutputDto> getAllCart();

    List<CartOutputDto> getCartByUserId(Long userid);

    void deleteCart(Long cartId);

    void orderCart(OrderRequestDto orderRequestDto);
}
