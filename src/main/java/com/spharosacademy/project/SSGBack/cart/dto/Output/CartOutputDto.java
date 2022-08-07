package com.spharosacademy.project.SSGBack.cart.dto.Output;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartOutputDto {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartDtoOutput {

        private Long id;
        private String productName;
        private int price;
        private int qty;

        public static CartDtoOutput of (Cart cart, Product product){
            return CartDtoOutput.builder()
                    .id(cart.getId())
                    .productName(cart.getProduct().getProductName())
                    .qty(cart.getQty())
                    .price(cart.getProduct().getPrice())
                    .build();
        }
    }
}
