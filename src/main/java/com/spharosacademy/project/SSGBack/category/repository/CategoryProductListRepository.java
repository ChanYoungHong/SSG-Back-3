package com.spharosacademy.project.SSGBack.category.repository;

import com.spharosacademy.project.SSGBack.category.entity.CategoryProductList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {

    List<CategoryProductList> findAllByProductId(Long productId);

}
