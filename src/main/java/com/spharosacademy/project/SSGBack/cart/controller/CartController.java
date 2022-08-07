package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.CartDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //상품상세페이지에서 장바구니 담기 클릭시
    @PostMapping("/cart/add")
    public Cart addCart(@RequestBody CartInputDto cartInputDto){
        return cartService.addCart(cartInputDto);
    }

    @GetMapping("/cart/getAll")
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/cart/getAllByUserId/{id}")
    private List<Cart> getAllCartByUserId(@PathVariable Long id){
        return cartService.getAllCartByUserId(id);
    }

}
