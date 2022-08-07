package com.spharosacademy.project.SSGBack.cart.dto;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long userId;
    private Long productId;

    private Product product;
}
