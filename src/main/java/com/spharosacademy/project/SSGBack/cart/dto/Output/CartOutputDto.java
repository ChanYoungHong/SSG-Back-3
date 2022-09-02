package com.spharosacademy.project.SSGBack.cart.dto.Output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
        private Long count;
        private float oldprice;
        private float newprice;
        private int qty;
        private String titleImgUrl;
        private int stock;

        OptionCartOutputDto optionCartOutputDto;
    }

