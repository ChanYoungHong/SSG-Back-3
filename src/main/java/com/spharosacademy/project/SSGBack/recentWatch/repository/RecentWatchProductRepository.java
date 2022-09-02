package com.spharosacademy.project.SSGBack.recentWatch.repository;

import com.spharosacademy.project.SSGBack.recentWatch.entity.RecentWatchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecentWatchProductRepository extends JpaRepository<RecentWatchProduct, Long> {

    @Query(value = "select r.id from RecentWatchProduct r where r.user.id=:userId and r.product.id=:productId")
    Long findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    List<RecentWatchProduct> findAllByUserId(Long userId);

}
