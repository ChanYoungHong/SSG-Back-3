package com.spharosacademy.project.SSGBack.product.Image.repository;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTitleImgRepository extends JpaRepository<ProductTitleImage, Long> {
    List<ProductTitleImage> findAllByProductId(Long productId);
}
