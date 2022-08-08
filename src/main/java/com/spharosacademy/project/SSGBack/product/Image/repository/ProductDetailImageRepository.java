package com.spharosacademy.project.SSGBack.product.Image.repository;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailImageRepository  extends JpaRepository<ProductDetailImage, Long> {
    //상품 id별로 이미지를 찾는 메소드 추가
    List<ProductDetailImage> findAllByProductId(Long productId);
}
