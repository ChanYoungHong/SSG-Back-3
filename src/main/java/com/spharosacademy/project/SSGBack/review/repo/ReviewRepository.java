package com.spharosacademy.project.SSGBack.review.repo;

import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUserId(Long userId);
}
