package com.spharosacademy.project.SSGBack.review.Image.repo;//package com.spharosacademy.project.SSGBack.review.Image.repo;

import com.spharosacademy.project.SSGBack.review.Image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ReviewImageRepo extends JpaRepository<ReviewImage, Long> {

    List<ReviewImage> findAllByReview(Review review);
}
