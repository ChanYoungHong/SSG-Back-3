package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.product.domain.LastCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILastCategoryRepository extends JpaRepository<LastCategory, Integer> {
}
