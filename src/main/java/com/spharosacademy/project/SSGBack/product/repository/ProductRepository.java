package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product AS p where p.name like %:searchWord% " +
            "or p.brand like %:searchWord%")
    List<Product> findAllBysearchWord(@Param("searchWord") String searchWord, Pageable pageable);

}
