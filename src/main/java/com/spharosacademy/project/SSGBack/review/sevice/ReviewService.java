package com.spharosacademy.project.SSGBack.review.sevice;

import com.spharosacademy.project.SSGBack.review.dto.input.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.input.UpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ProductReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.dto.output.UserReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;

import java.util.List;

public interface ReviewService {

    void addReview(ReviewInputDto reviewInputDto);

    void deleteReviewById(Long reviewId);

    List<UserReviewResponseDto> getReviewByuserId(Long userId);

    Review editReviewById(UpdateReviewDto updateReviewDto);

    List<ProductReviewResponseDto> getAllByProductId(Long productId);
}
