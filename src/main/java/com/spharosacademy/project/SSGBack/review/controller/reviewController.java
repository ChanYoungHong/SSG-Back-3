package com.spharosacademy.project.SSGBack.review.controller;


import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.ResponseReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class reviewController {

    private final ReviewService reviewService;


    // 리뷰 쓰기
    @PostMapping("/add")
    public Review addReview(@RequestBody RequestReviewDto requestReviewDto) {
        return reviewService.addReview(requestReviewDto);
//    public void addReview(@RequestBody RequestReviewDto requestReviewDto, MultipartFile file) throws Exception {
//        reviewService.addReview(requestReviewDto, file);
    }

    // 모든 상품 조회 하기
    @GetMapping("/all")
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    // 특정 리뷰 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteReviewById(@PathVariable Long reviewId) throws Exception {
        reviewService.deleteReviewById(reviewId);
    }

    // 특정 리뷰 수정
    @PutMapping("/edit/")
    public Review editReviewById(@RequestBody ResponseReviewDto responseReviewDto) throws Exception {
        return reviewService.editReviewById(responseReviewDto);
    }

}
