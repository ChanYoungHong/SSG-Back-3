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
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    //장바구니 담기 클릭
    @PostMapping("/add")
    public String addCart(@RequestBody CartInputDto cartInputDto){
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.addProductToCart(cartInputDto, userId);
        return "선택하신 상품이 장바구니에 담겼습니다";
    }

    @GetMapping("/getAll")
    public List<CartOutputDto> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("/getByUserId")
    public List<CartOutputDto> getCartByUserId(){
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return cartService.getCartByUserId(userId);
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
    public ResponseEntity<String> orderCart(@RequestBody CartOrderRequestDto cartOrderRequestDto){
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.orderCart(cartOrderRequestDto, userId);
         return ResponseEntity.status(HttpStatus.OK).body("주문이 완료되었습니다");
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
