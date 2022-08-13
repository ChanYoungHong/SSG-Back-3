package com.spharosacademy.project.SSGBack.category.repository;

import com.spharosacademy.project.SSGBack.category.entity.CategoryL;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMRepository extends JpaRepository<CategoryM, Integer> {

   List<CategoryM> findAllByCategoryL(CategoryL categoryL);

}
