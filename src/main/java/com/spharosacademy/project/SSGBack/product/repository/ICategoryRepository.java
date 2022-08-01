package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
