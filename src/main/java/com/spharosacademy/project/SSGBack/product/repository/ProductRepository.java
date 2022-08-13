package com.spharosacademy.project.SSGBack.product.repository;

import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findAllbyCategoryM(CategoryM categoryM);

//    List<Product> findAllByCategoryS(CategoryS categoryS);

//    List<Product> findAllByCategorySS(CategorySS categorySS);
}

