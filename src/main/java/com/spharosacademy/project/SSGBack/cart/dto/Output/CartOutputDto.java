package com.spharosacademy.project.SSGBack.cart.dto.Output;

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
        private String titleImgUrl;
    }

