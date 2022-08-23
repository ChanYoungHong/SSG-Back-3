package com.spharosacademy.project.SSGBack.review.controller;

import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    
    @PostMapping("/add")
    public String addReview(@RequestBody RequestReviewDto requestReviewDto){
        reviewService.addReview(requestReviewDto);  
        return "리뷰 등록";
    }
}
