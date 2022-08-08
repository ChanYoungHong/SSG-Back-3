package com.spharosacademy.project.SSGBack.product.Image.service;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;

import java.util.List;

public interface ProductDetailImgService {
    List<ProductDetailImage> getImgByProductId(Long productId);
}
