package com.spharosacademy.project.SSGBack.cart.service;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.CartDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.repository.CartRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServiceimple implements CartService {

    private final IUserRepository iUserRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    @Override
    public Cart addCart(CartInputDto cartInputDto) {
        return cartRepository.save(Cart.builder().
                product(productRepository.findById(cartInputDto.getProductId()).get())
                .user(iUserRepository.findById(cartInputDto.getUserId()).get())
                .qty(cartInputDto.getQty())
                .build());
    }

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
//    public CartDtoOutput getCartById(Long id) {
//        Cart cart = iCartRepository.findById(id).get();
//        return CartDtoOutput.builder()
//                .id(cart.getId())
//                .productName(cart.getProduct().getName())
//                .qty(cart.getQty())
//                .price(cart.getProduct().getPrice())
//                .build();
//    }

    @Override
    public Product addCartProduct(Long productId) {
        return null;
    }

    @Override
    public List<Cart> getAllCart() {
        return null;
    }

    @Override
    public List<Cart> getAllCartByUserId(Long id) {
        return null;
    }
}
