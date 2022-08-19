package com.spharosacademy.project.SSGBack.review.controller;


import com.spharosacademy.project.SSGBack.qna.dto.QnaInputDto;
import com.spharosacademy.project.SSGBack.qna.dto.QnaOutputDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewOutputDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.jar.Attributes;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class reviewController {

    private final ReviewService reviewService;


    // 리뷰 쓰기
    @PostMapping("/add")
    public String addReview(@RequestBody ReviewInputDto reviewInputDto, Model model, MultipartFile file) throws Exception {
        reviewService.addReview(reviewInputDto, file);
        model.addAttribute("message","글 작성이 완료 되었습니다");
        return "글 작성이 완료 되었습니다";
    }

    // 모든 상품 조회 하기
    @GetMapping("/all")
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    // 특정 리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public String deleteReviewById(@PathVariable Long reviewId, Model model) throws Exception {
        reviewService.deleteReviewById(reviewId);

        model.addAttribute("message", "글 삭제가 완료 되었습니다");

        return "글 삭제가 완료 되었습니다";
    }


    // 특정 리뷰 수정
    @PutMapping("/edit")
    public String editReviewById(@RequestBody ReviewOutputDto reviewOutputDto,Model model) throws Exception {
        reviewService.editReviewById(reviewOutputDto);
        model.addAttribute("message", "글 수정이 완료 되었습니다");
        return "글 수정이 완료 되었습니다";
    }


    @GetMapping("/{productid}")
    public List<ReviewOutputDto> getReviewByProductId(@PathVariable Long productid) {
        return reviewService.getReviewByProductId(productid);
    }

}
