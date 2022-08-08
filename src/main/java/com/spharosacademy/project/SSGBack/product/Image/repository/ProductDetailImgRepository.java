package com.spharosacademy.project.SSGBack.product.Image.repository;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;

import java.util.List;

public interface ProductDetailImgRepository extends JpaRepository<ProductDetailImage, Long>{
    List<ProductDetailImage> findAllByProductId(Long productId);
}
