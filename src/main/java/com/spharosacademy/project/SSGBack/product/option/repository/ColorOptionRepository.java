package com.spharosacademy.project.SSGBack.product.option.repository;

import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ColorOptionRepository extends JpaRepository<ColorOption, Long> {
    List<ColorOption> findAllByProductId(Long productId);
}
