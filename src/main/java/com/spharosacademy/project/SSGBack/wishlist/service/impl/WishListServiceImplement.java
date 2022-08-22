package com.spharosacademy.project.SSGBack.wishlist.service.impl;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.repository.UserRepository;
import com.spharosacademy.project.SSGBack.wishlist.dto.input.RequestWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.dto.output.ResponseWishListDto;
import com.spharosacademy.project.SSGBack.wishlist.entity.WishList;
import com.spharosacademy.project.SSGBack.wishlist.repository.WishListRepository;
import com.spharosacademy.project.SSGBack.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListServiceImplement implements WishListService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishListRepository wishListRepository;

    @Override
    public void addProduct(RequestWishListDto requestWishListDto) {
        Optional<Product> product = productRepository.findById(requestWishListDto.getProductId());
        Optional<User> user = userRepository.findById(requestWishListDto.getUserId());

        if (product.isPresent() && user.isPresent()) {
            wishListRepository.save(WishList.builder()
                    .product(product.get())
                    .user(user.get())
                    .build());
        }
    }

    @Override
    public List<ResponseWishListDto> findProductById(Long userid) {
        List<WishList> wishLists = wishListRepository.findByUserId(userid);
        List<ResponseWishListDto> responseWishListDtos = new ArrayList<>();

        for (WishList wishList : wishLists) {
            responseWishListDtos.add(ResponseWishListDto.builder()
                    .productId(wishList.getProduct().getId())
                    .productName(wishList.getProduct().getName())
                    .price(wishList.getProduct().getNewPrice())
                    .brand(wishList.getProduct().getBrand())
                    .mallTxt(wishList.getProduct().getMallText())
                    .priceTxt(wishList.getProduct().getPriceText())
                    .thumbnailImgUrl(wishList.getProduct().getThumbnailUrl())
                    .stock(wishList.getProduct().getCnt())
                    .build());
        }
        return responseWishListDtos;
    }

    @Override
    public void deleteWishList(Long wishListId) {
        wishListRepository.deleteById(wishListId);
    }


}
