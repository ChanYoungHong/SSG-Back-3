package com.spharosacademy.project.SSGBack.wishlist.controller;

import com.spharosacademy.project.SSGBack.wishlist.dto.input.RequestWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.dto.output.ResponseWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishList")
@RequiredArgsConstructor
@CrossOrigin
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/add")
    public void addWishList(@RequestBody RequestWishListDto requestWishListDto) {
        wishListService.addProduct(requestWishListDto);
    }

    @GetMapping("/find/{userid}")
    public List<ResponseWishListDto> findById(@PathVariable Long userid) {
        return wishListService.findProductById(userid);
    }

    @DeleteMapping("/delete/{wishlistId}")
    public void deleteWishList(@PathVariable Long wishlistId) {
        wishListService.deleteWishList(wishlistId);
    }
}
