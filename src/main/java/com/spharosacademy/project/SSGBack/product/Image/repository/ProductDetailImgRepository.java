package com.spharosacademy.project.SSGBack.product.Image.repository;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;

import java.util.List;

public interface ProductDetailImgRepository extends {
    List<ProductDetailImage> findAllByProductId(Long productId);
}
JpaRepository<ProductDetailImage, Long>