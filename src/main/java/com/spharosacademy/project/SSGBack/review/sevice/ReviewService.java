package com.spharosacademy.project.SSGBack.review.sevice;

import com.spharosacademy.project.SSGBack.review.dto.input.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    void addReview(ReviewInputDto reviewInputDto);

    void deleteReviewById(Long reviewId);

    List<ReviewResponseDto> getReviewByuserId(Long userId);
}
