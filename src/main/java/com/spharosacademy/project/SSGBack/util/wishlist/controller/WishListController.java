package com.spharosacademy.project.SSGBack.util.wishlist.controller;

import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import com.spharosacademy.project.SSGBack.util.wishlist.service.WishListService;
import com.spharosacademy.project.SSGBack.util.wishlist.dto.input.RequestWishListDto;
import com.spharosacademy.project.SSGBack.util.wishlist.dto.output.ResponseWishListDto;
import com.spharosacademy.project.SSGBack.util.wishlist.dto.output.ResponseWishListIdDto;
import com.spharosacademy.project.SSGBack.util.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishList")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;
    private final WishListRepository wishListRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public ResponseEntity<ResponseWishListIdDto> addWishList(@RequestBody RequestWishListDto requestWishListDto) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));

        wishListService.addProduct(requestWishListDto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWishListIdDto.builder()
                .wishListId(wishListRepository.findByUserIdAndProductId(userId, requestWishListDto.getProductId()))
                .build());

    }

    @GetMapping("/find")
    public List<ResponseWishListDto> findById() {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return wishListService.findProductById(userId);
    }

    @DeleteMapping("/delete/{wishlistId}")
    public void deleteWishList(@PathVariable Long wishlistId) {
        wishListService.deleteWishList(wishlistId);
    }
}
