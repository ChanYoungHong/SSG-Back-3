package com.spharosacademy.project.SSGBack.product.option.repository;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.option.entity.SizeOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SizeOptionRepository extends JpaRepository<SizeOption, Long> {
    List<SizeOption> findAllByProduct(Product product);
}
