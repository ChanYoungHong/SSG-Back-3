package com.spharosacademy.project.SSGBack.cart.service;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;

import java.util.List;

public interface CartService {

    Cart addProductToCart(CartInputDto cartInputDto);

    List<CartOutputDto> getAllCart();

    CartOutputDto getCartByUserId(Long userid);
}
