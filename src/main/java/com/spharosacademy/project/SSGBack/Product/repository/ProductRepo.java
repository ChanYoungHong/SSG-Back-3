package com.spharosacademy.project.SSGBack.Product.repository;

import com.spharosacademy.project.SSGBack.Product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
