package com.spharosacademy.project.SSGBack.cart.dto.Output;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartOutputDto {

        private Long id;
        private String productName;
        private String productBrand;
        private String productColor;
        private int price;
        private int totalprice;
        private int qty;

        public static CartOutputDto of (Cart cart, Product product){
            return CartOutputDto.builder()
                    .id(cart.getId())
                    .productName(cart.getProduct().getProductName())
                    .productColor(cart.getProduct().getProductColor())
                    .productBrand(cart.getProduct().getProductBrand())
                    .qty(cart.getQty())
                    .price(cart.getProduct().getPrice())
                    .totalprice(product.getPrice())
                    .build();
        }
    }

