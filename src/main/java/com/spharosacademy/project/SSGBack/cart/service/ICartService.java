package com.spharosacademy.project.SSGBack.cart.service;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.CartDto;

import java.util.List;

public interface ICartService {

    Cart addCart(CartDto cartDto);

    List<Cart> getAllCart();

    List<Cart> getAllCartByUserId(Long id);
}
