package com.spharosacademy.project.SSGBack.review.controller;

import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDeleteDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestUpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ProductReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.dto.output.UserReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public String addReview(@RequestBody RequestReviewDto requestReviewDto)
            throws Exception {
        reviewService.addReview(requestReviewDto);
        return "글 작성이 완료 되었습니다";
    }

    //  상품에 대한 모든 리뷰 조회 하기
    @GetMapping("/product/{productId}")
    public List<ProductReviewResponseDto> getAllByProductId(@PathVariable Long productId) {
        return reviewService.getAllByProductId(productId);
    }

    // 특정 리뷰 삭제
    @DeleteMapping("/delete")
    public String deleteReviewById(@RequestBody RequestReviewDeleteDto requestReviewDeleteDto) throws Exception {
        reviewService.deleteReviewById(requestReviewDeleteDto);
        return "글 삭제가 완료 되었습니다";
    }

    //
//
    // 특정 리뷰 수정
    @PutMapping("/edit")
    public String editReviewById(@RequestBody RequestUpdateReviewDto requestUpdateReviewDto)
            throws Exception {
        reviewService.editReviewById(requestUpdateReviewDto);
        return "리뷰가 정상적으로 수정되었습니다";
    }

    //
//
    //사용자가 작성한 모든 리뷰 조회하는 화면
    @GetMapping("/user/{userId}")
    public List<UserReviewResponseDto> getReviewByProductId(@PathVariable Long userId) {
        return reviewService.getReviewByuserId(userId);
    }
}
