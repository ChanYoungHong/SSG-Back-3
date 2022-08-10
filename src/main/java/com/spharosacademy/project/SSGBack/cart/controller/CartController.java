package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
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

    //장바구니 담기 클릭
    @PostMapping("/add")
    public Cart addCart(@RequestBody CartInputDto cartInputDto){
        return cartService.addProductToCart(cartInputDto);
    }


    @GetMapping("/getAll")
    public List<CartOutputDto> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/getAllByUserId/{userid}")
    public List<CartOutputDto> getAllCartByUserId(@PathVariable Long userid){
        return cartService.getAllCartByUserId(userid);

    }

    @GetMapping("/search/{searchWord}")
    String searchList(@PathVariable String searchWord){
        return null;
    }
}
