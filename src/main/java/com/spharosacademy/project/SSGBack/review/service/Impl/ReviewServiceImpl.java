package com.spharosacademy.project.SSGBack.review.service.Impl;


import com.spharosacademy.project.SSGBack.review.dto.ReviewRequestDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepo;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;

    //상품평 쓰기
    @Override
    public void addReview(ReviewResponseDto createRequest) {
        return reviewRepo.
                save(Review.builder()
                        .reviewAuthorId(createRequest.getReviewAuthorId())
                        .reviewContent(createRequest.getReviewContent())
                        .reviewScore(createRequest.getReviewScore())
                        .reviewRegDate(createRequest.getReviewRegDate())
                .build();
    }

    //상품평 수정

    @Override
    public void editReview(ReviewResponseDto editRequest) {


    }

    // 상품평 삭제

    @Override
    public void deleteReview(Long productId) {

    }

    @Override
    public List<Review> getAll() {
        return null;
    }
}
