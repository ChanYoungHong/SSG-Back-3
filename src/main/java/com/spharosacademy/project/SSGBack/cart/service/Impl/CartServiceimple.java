package com.spharosacademy.project.SSGBack.cart.service.Impl;

import com.spharosacademy.project.SSGBack.cart.dto.Output.OptionCartOutputDto;
import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.Output.CartOutputDto;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.repository.CartRepository;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
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
    private final OptionRepository optionRepository;


    @Override
    public Cart addProductToCart(CartInputDto cartInputDto) {
        //상품의 존재 여부를 판단한다
        Optional<Product> product = productRepository.findById(cartInputDto.getProductId());
        Optional<User> user = iUserRepository.findById(cartInputDto.getUserId());
        Optional<OptionList> optionList = optionRepository.findById(cartInputDto.getOptionId());

        try {
            if (product.isPresent() && user.isPresent() && optionList.isPresent()) {
                return cartRepository.save(
                        Cart.builder()
                                .product(product.get())
                                .user(user.get())
                                .qty(cartInputDto.getQty())
                                .optionId(optionList.get().getId())
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
                    .productid(cart.getProduct().getId())
                    .productName(cart.getProduct().getName())
                    .titleImgUrl(cart.getProduct().getThumbnailUrl())
                    .useraddress(cart.getUser().getAddress())
                    .username(cart.getUser().getName())
                    .productBrand(cart.getProduct().getBrand())
                    .price(cart.getProduct().getNewPrice())
                    .qty(cart.getQty())
                    .build());
        });
        return cartOutputDtoList;
    }

    @Override
    public List<CartOutputDto> getCartByUserId(Long userid) {
        List<Cart> carts = cartRepository.findByUserId(userid);
        List<CartOutputDto> outputDtos = new ArrayList<>();

        for (Cart cart : carts) {
            outputDtos.add(CartOutputDto.builder()
                    .id(cart.getId())
                    .productid(cart.getProduct().getId())
                    .productName(cart.getProduct().getName())
                    .titleImgUrl(cart.getProduct().getThumbnailUrl())
                    .useraddress(cart.getUser().getAddress())
                    .username(cart.getUser().getName())
                    .productBrand(cart.getProduct().getBrand())
                    .price(cart.getProduct().getNewPrice())
                    .qty(cart.getQty())
                    .optionCartOutputDto(OptionCartOutputDto.builder()
                            .color(optionRepository.findById(cart.getOptionId())
                                    .get().getColor())
                            .size(optionRepository.findById(cart.getOptionId())
                                    .get().getSize())
                            .build())
                    .build());
        }
        return outputDtos;
    }
}


