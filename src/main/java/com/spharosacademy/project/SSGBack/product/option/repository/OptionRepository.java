package com.spharosacademy.project.SSGBack.product.option.repository;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OptionRepository extends JpaRepository<OptionList, Long> {

    List<OptionList> findAllByProduct(Product product);

    @Query(value = "select p from OptionList as p where p.colors.id = :colorId AND p.size.id = :sizeId")
    OptionList findByColorsAndSize(Long colorId, Long sizeId);

    List<OptionList> findByProductId(Long productId);
}