package com.spharosacademy.project.SSGBack.product.Image.repository;

import com.spharosacademy.project.SSGBack.product.Image.entity.ImageList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageListRepository extends JpaRepository<ImageList, Long> {
    List<ImageList> findAllByProductId(Long productId);
}
