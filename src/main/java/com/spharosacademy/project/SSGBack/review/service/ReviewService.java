package com.spharosacademy.project.SSGBack.review.service;

import com.spharosacademy.project.SSGBack.review.dto.ReviewRequestDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;

import java.util.List;

public interface ReviewService {

    //상품평 추가 서비스

    void addReview(ReviewResponseDto createRequest);

    // 상품명 수정 서비스

    void editReview(ReviewResponseDto editRequest);

    //상품평 제거 서비스

    void deleteReview(Long productId);

    // 상품평 전체 조회

    List<Review> getAll();

}
