package com.spharosacademy.project.SSGBack.product.repo;

import com.spharosacademy.project.SSGBack.product.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product AS p where p.name like %:query% " +
        "or p.brand like %:query%")
    Page<Product> searchBysearchWord(@Param("query") String query, Pageable pageable);
}
