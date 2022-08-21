package com.spharosacademy.project.SSGBack.review.sevice;

import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDeleteDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestUpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ProductReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.dto.output.UserReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;

import java.util.List;

public interface ReviewService {

    void addReview(RequestReviewDto requestReviewDto);

    void deleteReviewById(RequestReviewDeleteDto requestReviewDeleteDto);

    List<UserReviewResponseDto> getReviewByuserId(Long userId);

    Review editReviewById(RequestUpdateReviewDto requestUpdateReviewDto);

    List<ProductReviewResponseDto> getAllByProductId(Long productId);
}
