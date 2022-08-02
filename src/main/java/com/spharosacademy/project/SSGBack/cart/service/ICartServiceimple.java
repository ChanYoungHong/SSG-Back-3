package com.spharosacademy.project.SSGBack.cart.service;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.CartDto;
import com.spharosacademy.project.SSGBack.cart.repository.ICartRepository;
import com.spharosacademy.project.SSGBack.product.repository.IProductRepository;
import com.spharosacademy.project.SSGBack.product.service.IProductService;
import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ICartServiceimple implements ICartService {
    private final ICartRepository iCartRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;

    @Override
    public Cart addCart(CartDto cartDto) {
        return iCartRepository.save(
                Cart.builder()
                        .product(iProductRepository.findById(cartDto.getProductId()).get())
                        .user(iUserRepository.findById(cartDto.getUserId()).get())
                        .build()
        );
    }

    @Override
    public List<Cart> getAllCart() {
        return iCartRepository.findAll();
    }

    @Override
    public List<Cart> getAllCartByUserId(Long id) {
        return iCartRepository.findAllByUserId(id);
    }
}
