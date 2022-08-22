package com.spharosacademy.project.SSGBack.review.repo;

import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUserId(Long userId);

    List<Review> findAllByProductId(Long productId);

    @Query(value = "select count(r) as reviewCount, avg(r.reviewScore) as reviewAvg from Review r where r.product.id =:productId")
    ReviewTotalDto collectByProductId(@Param("productId") Long productId);
}
