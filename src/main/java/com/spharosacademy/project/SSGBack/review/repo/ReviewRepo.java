package com.spharosacademy.project.SSGBack.review.repo;

import com.spharosacademy.project.SSGBack.qna.entity.Qna;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    Long countByProductId(Long productid);
    List<Review> findByProductId(Long productid);

}
