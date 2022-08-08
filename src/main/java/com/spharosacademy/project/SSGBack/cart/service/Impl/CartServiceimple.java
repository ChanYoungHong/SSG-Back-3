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


//    @Override
//    public Cart addCart(CartInputDto cartInputDto) {
//        return cartRepository.save(Cart.builder().
//                product(productRepository.findById(cartInputDto.getProductId()).get())
//                .user(iUserRepository.findById(cartInputDto.getUserId()).get())
//                .qty(cartInputDto.getQty())
//                .build());
//    }

//    @Override
//    public List<CartDtoOutput> getAll() {
//        List<Cart> cartList = iCartRepository.findAll();
//        List<CartDtoOutput> cartDtoOutputList = new ArrayList<>();
//
//        cartDtoOutputList = (List<CartDtoOutput>) cartList.stream().map(cart -> {
//            return Cart.add(
//                    CartDtoOutput.builder()
//                            .id(cart.getId())
//                            .productName(cart.getProduct().getName())
//                            .qty(cart.getQty())
//                            .price(cart.getProduct().getPrice())
//                            .build()
//            );
//        });
//
//
//    }

//    @Override
//    public CartOutputDto getCartById(Long id) {
//        Cart cart = cartRepository.findById(id).get();
//        return cart.builder()
//                .id(cart.getId())
//                .productName(cart.getProduct().getProductName())
//                .qty(cart.getQty())
//                .price(cart.getProduct().getPrice())
//                .build();
//    }

//    @Override
//    public Product addCartProduct(Long productId) {
//        return null;
//    }

    @Override
    public Cart addProductToCart(CartInputDto cartInputDto) {
        //상품의 존재 여부를 판단한다
        Optional<Product> product = productRepository.findById(cartInputDto.getProductId());
        Optional<User> user = iUserRepository.findById(cartInputDto.getUserId());
        try {
            if (product.isPresent() && user.isPresent()) {
                return cartRepository.save(Cart.builder()
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
    public List<Cart> getAllCart() {
        return null;
    }

    @Override
    public CartOutputDto getAllCartByUserId(Long id) {

        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return CartOutputDto.builder()
                    .id(cart.get().getId())
                    .productName(cart.get().getProduct().getProductName())
                    .price(cart.get().getProduct().getPrice())
                    .productBrand(cart.get().getProduct().getProductBrand())
                    .qty(cart.get().getQty())
                    .productColor(cart.get().getProduct().getProductColor())
                    .build();
        } else {
            return null;
        }
    }
//        List<CartOutputDto> cartOutputDto = new ArrayList<>();
//        cartOutputDto = (List<CartOutputDto>) cartOutputDto.stream().map(cart -> {
//            return cartOutputDto.add(
//                    CartOutputDto.builder()
//                            .id(cart.getId())
//                            .productName(cart.getProductName())
//                            .qty(cart.getQty())
//                            .price(cart.getPrice())
//                            .build()
//            );
//        });
//    };
}
