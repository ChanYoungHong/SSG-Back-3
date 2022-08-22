package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.Output.OrderStockOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartOrderRequestDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartUpdateRequestDto;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.order.exception.OutOfStockException;
import com.spharosacademy.project.SSGBack.product.option.dto.output.OptionOutputDto;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
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

    //장바구니 담기 클릭
    @PostMapping("/add")
    public String addCart(@RequestBody CartInputDto cartInputDto){
        cartService.addProductToCart(cartInputDto);
        return "선택하신 상품이 장바구니에 담겼습니다";
    }

    @GetMapping("/getAll")
    public List<CartOutputDto> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/getByUserId/{userid}")
    public List<CartOutputDto> getCartByUserId(@PathVariable Long userid){
        return cartService.getCartByUserId(userid);
    }

    @GetMapping("/getOptionList/{productId}")
    public List<OptionList> getOptionByProduct(@PathVariable Long productId){
        return cartService.getOptionByProduct(productId);
    }

    @DeleteMapping("/delete/{cartId}")
    public String deleteCart(@PathVariable Long cartId){
        cartService.deleteCart(cartId);
        return "선택하신 상품이 장바구니에서 삭제되었습니다";
    }

    @PostMapping("/order")
    public List<OrderStockOutputDto> orderCart(@RequestBody CartOrderRequestDto cartOrderRequestDto){
         return cartService.orderCart(cartOrderRequestDto);
    }

    @PutMapping("/update")
    public String updateCart(@RequestBody CartUpdateRequestDto cartUpdateRequestDto){
        cartService.updateCart(cartUpdateRequestDto);
        return "장바구니 상품 옵션이 변경되었습니다";
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> handleOutofStockException(OutOfStockException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
