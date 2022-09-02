package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.Output.ChangedQtyDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartOrderRequestDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartUpdateRequestDto;
import com.spharosacademy.project.SSGBack.cart.repository.CartRepository;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.order.exception.OutOfStockException;
import com.spharosacademy.project.SSGBack.product.exception.CartNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart/user")
@RequiredArgsConstructor
@CrossOrigin
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CartRepository cartRepository;

    //장바구니 담기 클릭
    @PostMapping("/add")
    public String addCart(@RequestBody CartInputDto cartInputDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.addProductToCart(cartInputDto, userId);
        return "선택하신 상품이 장바구니에 담겼습니다";
    }

    @PostMapping("/pQty/{id}")
    public ResponseEntity<ChangedQtyDto> increaseQty(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.incQty(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body(ChangedQtyDto.builder()
                .qty(cartRepository.findById(id).get().getQty() + 1)
                .build());
    }

    @PostMapping("/dQty/{id}")
    public ResponseEntity<ChangedQtyDto> decreaseQty(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.decQty(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body(ChangedQtyDto.builder()
                .qty(cartRepository.findById(id).get().getQty() - 1)
                .build());
    }

    @GetMapping("/getAll")
    public List<CartOutputDto> getAllCart() {
        return cartService.getAllCart();
    }

    @GetMapping("/getByUserId")
    public List<CartOutputDto> getCartByUserId() {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return cartService.getCartByUserId(userId);
    }

    @GetMapping("/getOptionList/{productId}")
    public List<OptionList> getOptionByProduct(@PathVariable Long productId) {
        return cartService.getOptionByProduct(productId);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.deleteCart(cartId, userId);
        List<CartOutputDto> cartOutputDtoList = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body("장바구니에 담으신 상품이 삭제되었습니다!");
    }

    @PostMapping("/order")
    public ResponseEntity<String> orderCart(@RequestBody CartOrderRequestDto cartOrderRequestDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.orderCart(cartOrderRequestDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body("주문이 완료되었습니다");
    }

    @PutMapping("/update")
    public String updateCart(@RequestBody CartUpdateRequestDto cartUpdateRequestDto) {
        cartService.updateCart(cartUpdateRequestDto);
        return "장바구니 상품 옵션이 변경되었습니다";
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> handleOutofStockException(OutOfStockException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(OptionNotFoundException.class)
    public ResponseEntity<String> handleOptionNotFoundException(OptionNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
