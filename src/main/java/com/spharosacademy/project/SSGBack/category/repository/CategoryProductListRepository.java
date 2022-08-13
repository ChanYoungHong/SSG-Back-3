package com.spharosacademy.project.SSGBack.category.repository;

import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.entity.CategoryProductList;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {

    List<CategoryProductList> findAllByCategoryM(CategoryM categoryM);

    List<CategoryProductList> findAllByProduct(Product product);

    List<CategoryProductList> findAllByCategoryS(CategoryS categoryS);

    List<CategoryProductList> findAllByCategorySS(CategorySS categorySS);

}
