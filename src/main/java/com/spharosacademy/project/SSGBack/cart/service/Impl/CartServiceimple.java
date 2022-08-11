package com.spharosacademy.project.SSGBack.cart.service.Impl;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.repository.CartRepository;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceimple implements CartService {

    private final IUserRepository iUserRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    @Override
    public Cart addProductToCart(CartInputDto cartInputDto) {
        //상품의 존재 여부를 판단한다
        Optional<Product> product = productRepository.findById(cartInputDto.getProductId());
        Optional<User> user = iUserRepository.findById(cartInputDto.getUserId());
        try {
            if (product.isPresent() && user.isPresent()) {
                return cartRepository.save(
                        Cart.builder()
                                .product(product.get())
                                .user(user.get())
                                .qty(cartInputDto.getQty())
                                .build());
            }
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CartOutputDto> getAllCart() {
        List<Cart> ListCart = cartRepository.findAll();
        List<CartOutputDto> cartOutputDtoList = new ArrayList<>();

        ListCart.forEach(cart -> {
            cartOutputDtoList.add(CartOutputDto.builder()
                    .id(cart.getId())
                    .username(cart.getUser().getName())
                    .useraddress(cart.getUser().getAddress())
                    .productid(cart.getProduct().getId())
                    .productName(cart.getProduct().getName())
                    .price(cart.getProduct().getPrice())
                    .productBrand(cart.getProduct().getBrand())
                    .qty(cart.getQty())
                    .build());
        });
        return cartOutputDtoList;
    }

    @Override
    public CartOutputDto getCartByUserId(Long userid) {
        Cart cart = cartRepository.findByUserId(userid).get(0);

        return CartOutputDto.builder()
                .id(cart.getId())
                .price(cart.getProduct().getPrice())
                .username(cart.getUser().getName())
                .useraddress(cart.getUser().getAddress())
                .qty(cart.getQty())
                .productid(cart.getProduct().getId())
                .productBrand(cart.getProduct().getBrand())
                .productName(cart.getProduct().getName())
                .build();
    }
}


