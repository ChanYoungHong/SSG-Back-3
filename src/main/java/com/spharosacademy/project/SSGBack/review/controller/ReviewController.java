package com.spharosacademy.project.SSGBack.review.controller;

import com.spharosacademy.project.SSGBack.review.dto.input.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
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
    public String addReview(@RequestBody ReviewInputDto reviewInputDto)
            throws Exception {
        reviewService.addReview(reviewInputDto);
        return "글 작성이 완료 되었습니다";
    }

        // 모든 리뷰 조회 하기
//    @GetMapping("/all")
//    public List<Review> getAll() {
//        return reviewService.getAll();
//    }

    // 특정 리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public String deleteReviewById(@PathVariable Long reviewId) throws Exception {
        reviewService.deleteReviewById(reviewId);
        return "글 삭제가 완료 되었습니다";
    }
//
//
//    // 특정 리뷰 수정
//    @PutMapping("/edit")
//    public String editReviewById(@RequestBody ReviewOutputDto reviewOutputDto, Model model)
//            throws Exception {
//        reviewService.editReviewById(reviewOutputDto);
//        model.addAttribute("message", "글 수정이 완료 되었습니다");
//        return "글 수정이 완료 되었습니다";
//    }
//
//
    //사용자가 작성한 모든 리뷰 조회하는 화면
    @GetMapping("/{userId}")
    public List<ReviewResponseDto> getReviewByProductId(@PathVariable Long userId) {
        return reviewService.getReviewByuserId(userId);
    }
}
