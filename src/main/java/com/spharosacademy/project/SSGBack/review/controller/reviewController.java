package com.spharosacademy.project.SSGBack.review.controller;


import com.spharosacademy.project.SSGBack.review.dto.ReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class reviewController {

    private final ReviewService reviewService;


    @PostMapping
    public Review addReview(@RequestBody ReviewResponseDto createRequest) {
        return ReviewService.addReview(createRequest)
    }

}
