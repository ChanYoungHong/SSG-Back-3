package com.spharosacademy.project.SSGBack.cart.service;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.CartDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;

import java.util.List;

public interface CartService {

    Cart addCart(CartInputDto cartInputDto);

    Product addCartProduct(Long productId);

    List<Cart> getAllCart();

    List<Cart> getAllCartByUserId(Long id);
}
