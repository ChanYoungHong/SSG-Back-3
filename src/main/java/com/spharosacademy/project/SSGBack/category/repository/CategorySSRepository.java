package com.spharosacademy.project.SSGBack.category.repository;

import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorySSRepository extends JpaRepository<CategorySS, Integer> {

}
