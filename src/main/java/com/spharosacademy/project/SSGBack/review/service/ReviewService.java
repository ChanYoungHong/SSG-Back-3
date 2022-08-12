package com.spharosacademy.project.SSGBack.review.service;

import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.ResponseReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;

import java.math.BigInteger;
import java.util.List;

public interface ReviewService {

    //상품평 추가 서비스

    Review addReview(RequestReviewDto requestReviewDto);

    // 상품명 수정 서비스
    Review editReviewById(ResponseReviewDto responseReviewDto) throws Exception;

    //상품평 제거 서비스
    void deleteReviewById(Long id) throws Exception;


    // 상품평 전체 조회
    List<Review> getAll();

}
