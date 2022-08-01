package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.domain.SmallCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {
}
