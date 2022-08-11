package com.spharosacademy.project.SSGBack.product.Image.repository;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDetailImgRepository extends JpaRepository<ProductDetailImage, Long>{
    ProductDetailImage findByProductId(Long productId);
}