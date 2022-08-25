package com.spharosacademy.project.SSGBack.wishlist.controller;

import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import com.spharosacademy.project.SSGBack.wishlist.dto.input.RequestWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.dto.output.ResponseWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.dto.output.ResponseWishListIdDto;
import com.spharosacademy.project.SSGBack.wishlist.repository.WishListRepository;
import com.spharosacademy.project.SSGBack.wishlist.service.WishListService;
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
        wishListService.addProduct(requestWishListDto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWishListIdDto.builder()
                .wishListId(wishListRepository.findByUserIdAndProductId(requestWishListDto.getUserId(), requestWishListDto.getProductId()))
                .build());

    }

    @GetMapping("/find")
    public List<ResponseWishListDto> findById() {
        String token = jwtTokenProvider.customResolveToken();
        Long userid = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return wishListService.findProductById(userid);
    }

    @DeleteMapping("/delete/{wishlistId}")
    public void deleteWishList(@PathVariable Long wishlistId) {
        wishListService.deleteWishList(wishlistId);
    }
}
