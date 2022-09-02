package com.spharosacademy.project.SSGBack.cart.controller;

import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.Output.ChangedQtyDto;
import com.spharosacademy.project.SSGBack.cart.dto.Output.OptionCartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartOrderRequestDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartUpdateRequestDto;
import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import com.spharosacademy.project.SSGBack.cart.repository.CartRepository;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.order.exception.OutOfStockException;
import com.spharosacademy.project.SSGBack.product.exception.CartNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ColorOutputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.SizeOutputDto;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
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
    private final OptionRepository optionRepository;

    //장바구니 담기 클릭
    @PostMapping("/add")
    public String addCart(@RequestBody CartInputDto cartInputDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.addProductToCart(cartInputDto, userId);
        return "선택하신 상품이 장바구니에 담겼습니다";
    }

    @PostMapping("/pQty/{id}")
    public ResponseEntity<CartOutputDto> increaseQty(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.incQty(id, userId);
        Cart cart = cartRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(CartOutputDto.builder()
                .id(cart.getId())
                .productid(cart.getProduct().getId())
                .productName(cart.getProduct().getName())
                .titleImgUrl(cart.getProduct().getThumbnailUrl())
                .useraddress(cart.getUser().getUserAddress())
                .username(cart.getUser().getUsername())
                .productBrand(cart.getProduct().getBrand())
                .newprice(cart.getProduct().getNewPrice())
                .oldprice(cart.getProduct().getOldPrice())
                .stock(optionRepository.findById(cart.getOptionId()).get().getStock())
                .qty(cart.getQty())
                .optionCartOutputDto(OptionCartOutputDto.builder()
                        .color(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getColors().getName())
                        .size(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getSize().getType())
                        .build())
                .count(cartRepository.countByUserId(userId))
                .build());
    }

    @PostMapping("/dQty/{id}")
    public ResponseEntity<CartOutputDto> decreaseQty(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.decQty(id, userId);
        Cart cart = cartRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(CartOutputDto.builder()
                .id(cart.getId())
                .productid(cart.getProduct().getId())
                .productName(cart.getProduct().getName())
                .titleImgUrl(cart.getProduct().getThumbnailUrl())
                .useraddress(cart.getUser().getUserAddress())
                .username(cart.getUser().getUsername())
                .productBrand(cart.getProduct().getBrand())
                .newprice(cart.getProduct().getNewPrice())
                .oldprice(cart.getProduct().getOldPrice())
                .stock(optionRepository.findById(cart.getOptionId()).get().getStock())
                .qty(cart.getQty())
                .optionCartOutputDto(OptionCartOutputDto.builder()
                        .color(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getColors().getName())
                        .size(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getSize().getType())
                        .build())
                .count(cartRepository.countByUserId(userId))
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

    //옵션 변경하기 선택했을 때 색상을 먼저
    @GetMapping("/color/{cartId}")
    public List<ColorOutputDto> getColorByCart(@PathVariable Long cartId) {
        return cartService.getColorByCart(cartId);
    }

    @GetMapping("/size/{cartId}/{colorId}")
    public List<SizeOutputDto> getSizeByCart(@PathVariable Long cartId, @PathVariable Long colorId) {
        return cartService.getSizeByCart(cartId, colorId);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<List<CartOutputDto>> deleteCart(@PathVariable Long cartId) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.deleteCart(cartId, userId);

        List<Cart> cartList = cartRepository.findByUserId(userId);
        List<CartOutputDto> cartOutputDtoList = new ArrayList<>();
        cartList.forEach(cart -> {
            cartOutputDtoList.add(CartOutputDto.builder()
                    .id(cart.getId())
                    .productid(cart.getProduct().getId())
                    .productName(cart.getProduct().getName())
                    .titleImgUrl(cart.getProduct().getThumbnailUrl())
                    .useraddress(cart.getUser().getUserAddress())
                    .username(cart.getUser().getUsername())
                    .productBrand(cart.getProduct().getBrand())
                    .newprice(cart.getProduct().getNewPrice())
                    .oldprice(cart.getProduct().getOldPrice())
                    .qty(cart.getQty())
                    .optionCartOutputDto(OptionCartOutputDto.builder()
                            .color(optionRepository.findById(cart.getOptionId())
                                    .orElseThrow(OptionNotFoundException::new).getColors().getName())
                            .size(optionRepository.findById(cart.getOptionId())
                                    .orElseThrow(OptionNotFoundException::new).getSize().getType())
                            .build())
                    .count(cartRepository.countByUserId(userId))
                    .build());
        });
        return ResponseEntity.status(HttpStatus.OK).body(cartOutputDtoList);
    }

    @PostMapping("/order")
    public ResponseEntity<String> orderCart(@RequestBody CartOrderRequestDto cartOrderRequestDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.orderCart(cartOrderRequestDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body("주문이 완료되었습니다");
    }

    @PutMapping("/update")
    public ResponseEntity<CartOutputDto> updateCart(@RequestBody CartUpdateRequestDto cartUpdateRequestDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        cartService.updateCart(cartUpdateRequestDto);
        Cart cart = cartRepository.findById(cartUpdateRequestDto.getCartId()).get();

        return ResponseEntity.status(HttpStatus.OK).body(CartOutputDto.builder()
                .id(cart.getId())
                .productid(cart.getProduct().getId())
                .productName(cart.getProduct().getName())
                .titleImgUrl(cart.getProduct().getThumbnailUrl())
                .useraddress(cart.getUser().getUserAddress())
                .username(cart.getUser().getUsername())
                .productBrand(cart.getProduct().getBrand())
                .newprice(cart.getProduct().getNewPrice())
                .oldprice(cart.getProduct().getOldPrice())
                .stock(optionRepository.findById(cart.getOptionId()).get().getStock())
                .qty(cart.getQty())
                .optionCartOutputDto(OptionCartOutputDto.builder()
                        .color(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getColors().getName())
                        .size(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getSize().getType())
                        .optionId(optionRepository.findById(cart.getOptionId())
                                .orElseThrow(OptionNotFoundException::new).getId())
                        .build())
                .count(cartRepository.countByUserId(userId))
                .build());
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
