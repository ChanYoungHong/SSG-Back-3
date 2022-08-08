package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
