package com.spharosacademy.project.SSGBack.product.repo;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
//
//    @Query("SELECT p from Product AS p where p.name like %:searchWord% " +
//        "or p.brand like %:searchWord%")
//    List<Product> findAllBysearchWord(@Param("searchWord") String searchWord);

    @Query("SELECT p from Product AS p where p.name like %:keyword% " +
            "or p.brand like %:keyword%")
    List<Product> findFirst20Bykeyword(String keyword);
//
//    @Query("SELECT p from Product AS p where p.name like %:keyword% " +
//            "or p.brand like %:keyword% order by p.sellAmt asc")
//    List<Product> findFirst20ByOrderBySellAmtAsc(String keyword, Pageable pageable);


}
