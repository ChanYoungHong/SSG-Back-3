package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.order.dto.input.CartOrderRequestDto;
import com.spharosacademy.project.SSGBack.cart.order.dto.input.OrderRequestDto;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.order.dto.input.OrderInputDto;
import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //장바구니 담기 클릭
    @PostMapping("/add")
    public Cart addCart(@RequestBody CartInputDto cartInputDto){
        return cartService.addProductToCart(cartInputDto);
    }

    @GetMapping("/getAll")
    public List<CartOutputDto> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/getByUserId/{userid}")
    public List<CartOutputDto> getCartByUserId(@PathVariable Long userid){
        return cartService.getCartByUserId(userid);
    }

    @DeleteMapping("/delete/{cartId}")
    public void deleteCart(@PathVariable Long cartId){
        cartService.deleteCart(cartId);
    }

    @PostMapping("/order")
    public void orderCart(@RequestBody OrderRequestDto orderRequestDto){
         cartService.orderCart(orderRequestDto);
    }
}
