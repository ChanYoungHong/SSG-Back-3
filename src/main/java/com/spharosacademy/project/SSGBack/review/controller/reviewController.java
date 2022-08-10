package com.spharosacademy.project.SSGBack.review.controller;


import com.spharosacademy.project.SSGBack.review.Image.service.ReviewImageService;
import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.ResponseReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class reviewController {

    private final ReviewService reviewService;
    private final ReviewImageService reviewImageService;


    // 리뷰 쓰기
    @PostMapping("/add")
    public Review addReview(@RequestBody RequestReviewDto requestReviewDto) {
        return reviewService.addReview(requestReviewDto);
    }

    // 모든 상품 조회 하기
    @GetMapping("/all")
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    // 특정 리뷰 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteReviewById(@PathVariable BigInteger id) throws Exception {
        reviewService.deleteReviewById(id);
    }

    // 특정 리뷰 수정
    @PutMapping("/edit/{id}")
    public Review editReview(@RequestBody ResponseReviewDto responseReviewDto) throws Exception {
        return reviewService.editReviewById(responseReviewDto);
    }

}
