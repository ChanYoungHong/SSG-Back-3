package com.spharosacademy.project.SSGBack.wishlist.service;

import com.spharosacademy.project.SSGBack.wishlist.dto.input.RequestWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.dto.output.ResponseWishListDto;

import java.util.List;

public interface WishListService {

    void addProduct(RequestWishListDto requestWishListDto, Long userId);

    List<ResponseWishListDto> findProductById(Long userId);

    void deleteWishList(Long wishListId);
}
