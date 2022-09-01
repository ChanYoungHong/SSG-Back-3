package com.spharosacademy.project.SSGBack.review.sevice;

import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDeleteDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestUpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseUserReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    void addReview(RequestReviewDto requestReviewDto, List<MultipartFile> multipartFileList, Long userId);

    void deleteReviewById(RequestReviewDeleteDto requestReviewDeleteDto, Long userId);

    List<ResponseUserReviewDto> getReviewByUserId(Long userId);

    Review editReviewById(RequestUpdateReviewDto requestUpdateReviewDto, Long userId);

    List<ResponseProductReviewDto> getAllByProductId(Long productId);

    void addImages(Long id, List<MultipartFile> multipartFileList);
}
