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
        private Long productid;
        private String username;
        private String useraddress;
        private String productName;
        private String productBrand;
        private String productColor;
        private int price;
        private int qty;

    }

