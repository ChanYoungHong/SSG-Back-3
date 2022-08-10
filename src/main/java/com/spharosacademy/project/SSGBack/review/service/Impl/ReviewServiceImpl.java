package com.spharosacademy.project.SSGBack.review.service.Impl;


import com.spharosacademy.project.SSGBack.review.Image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.Image.repo.ReviewImageRepo;
import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.ResponseReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepo;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ReviewImageRepo reviewImageRepo;


    //리뷰 사진 등록
    // 사진 등록 안하면?????

    @Override
    public Review addReview(RequestReviewDto requestReviewDto) {
        Review review = reviewRepo.save(Review.builder()
                        .reviewAuthorId(requestReviewDto.getReviewAuthorId())
                        .reviewContent(requestReviewDto.getReviewContent())
                        .reviewScore(requestReviewDto.getReviewScore())
                        .reviewRegDate(requestReviewDto.getReviewRegDate())
                .build());

        requestReviewDto.getReviewImageList().forEach(reviewImage -> {
            reviewImageRepo.save(ReviewImage.builder()
                            .imgUrl(reviewImage.getImgUrl())
                            .review(review)
                    .build());
        });
        return review;
    }

    // 리뷰 수정하기

    @Override
    public Review editReviewById(ResponseReviewDto responseReviewDto) throws Exception {
        Review review = reviewRepo.findById(responseReviewDto.getReviewId()).get();

        reviewRepo.save(Review.builder()
                .reviewAuthorId(responseReviewDto.getReviewAuthorId())
                .reviewContent(responseReviewDto.getReviewContent())
                .reviewScore(responseReviewDto.getReviewScore())
                .reviewModDate(responseReviewDto.getReviewModDate())
                .build());
        return review;
    }


    // 리뷰 삭제하기
    @Override
    public void deleteReviewById(BigInteger id) throws Exception {
        Optional<Review> deleteById = reviewRepo.findById(id);
        if (deleteById.isPresent()) {
            reviewRepo.deleteById(id);
        } else {
            throw  new Exception();
        }

    }

    // 리뷰 전체 페이지 조회
    @Override
    public List<Review> getAll() {
        List<Review> ListReview = reviewRepo.findAll();
        return ListReview;
    }
}