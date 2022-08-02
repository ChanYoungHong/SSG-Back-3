package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}