package com.spharosacademy.project.SSGBack.review.repo;

import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
