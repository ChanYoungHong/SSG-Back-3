package com.spharosacademy.project.SSGBack.review.service;

import com.spharosacademy.project.SSGBack.qna.dto.QnaOutputDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewOutputDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    //상품평 추가 서비스

    Review addReview(ReviewInputDto reviewInputDto, MultipartFile multipartFile) throws Exception;



    //수정중
//    void addReview(RequestReviewDto requestReviewDto, MultipartFile file) throws Exception;

    // 상품명 수정 서비스
    Review editReviewById(ReviewOutputDto reviewOutputDto) throws Exception;

    //상품평 제거 서비스
    void deleteReviewById(Long reviewId) throws Exception;


    // 상품평 전체 조회
    List<Review> getAll();


    List<ReviewOutputDto> getReviewByProductId(Long productid);

}
