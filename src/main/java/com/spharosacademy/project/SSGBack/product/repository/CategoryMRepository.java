package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.domain.CategoryM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMRepository extends JpaRepository<CategoryM, Integer>{

    List<CategoryM> findByCategoryLId(Integer id);
}
