package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //상품상세페이지에서 장바구니 담기 클릭시
//    @PostMapping("/add")
//    public Cart addCart(@RequestBody CartInputDto cartInputDto){
//        return cartService.addCart(cartInputDto);
//    }

    @GetMapping("/getAll")
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<CartOutputDto> getAllCartByUserId(@PathVariable User user){
        CartOutputDto cartOutputDto = cartService.getAllCartByUserId(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(cartOutputDto);
    }

}
