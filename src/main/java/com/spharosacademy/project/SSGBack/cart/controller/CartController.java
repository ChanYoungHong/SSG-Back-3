package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.CartDto;
import com.spharosacademy.project.SSGBack.cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/cart/add")
    public Cart addCart(@RequestBody CartDto cartDto){
        return iCartService.addCart(cartDto);
    }

    @GetMapping("/cart/getAll")
    public List<Cart> getAllCart(){
        return iCartService.getAllCart();
    }

    @GetMapping("/cart/getAllByUserId/{id}")
    private List<Cart> getAllCartByUserId(@PathVariable Long id){
        return iCartService.getAllCartByUserId(id);
    }
}
