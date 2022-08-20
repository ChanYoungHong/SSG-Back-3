package com.spharosacademy.project.SSGBack.review.sevice.impl;

import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.review.dto.input.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.output.OutputReviewImgDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.exception.NotOrderProductException;
import com.spharosacademy.project.SSGBack.review.image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.image.repo.ReviewImageRepository;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepository;
import com.spharosacademy.project.SSGBack.review.sevice.ReviewService;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final IUserRepository iUserRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public void addReview(ReviewInputDto reviewInputDto) {

        OrderDetail orderDetail = orderDetailRepository.findById(reviewInputDto.getOrderDetailId())
                .orElseThrow(NotOrderProductException::new);
        Product product = productRepository.findById(orderDetail.getProduct().getId())
                .orElseThrow(ProductNotFoundException::new);
        User user = iUserRepository.findById(reviewInputDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .user(user)
                .reviewContent(reviewInputDto.getReviewContent())
                .reviewScore(reviewInputDto.getReviewScore())
                .reviewTitle(reviewInputDto.getReviewTitle())
                .build());

        reviewInputDto.getInputReviewImageDtos().forEach(inputReviewImageDto -> {
            reviewImageRepository.save(ReviewImage.builder()
                    .reviewImgTxt(inputReviewImageDto.getReviewImgTxt())
                    .reviewImgUrl(inputReviewImageDto.getReviewImgUrl())
                    .review(review)
                    .build());
        });
    }

    @Override
    public void deleteReviewById(Long reviewId) {

    }

    @Override
    public List<ReviewResponseDto> getReviewByuserId(Long userId) {
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        List<OutputReviewImgDto> outputReviewImgDtos = new ArrayList<>();
        List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReviewId(userId);
        for (ReviewImage reviewImage : reviewImageList) {
            outputReviewImgDtos.add(OutputReviewImgDto.builder()
                    .reviewImgUrl(reviewImage.getReviewImgUrl())
                    .reviewImgTxt(reviewImage.getReviewImgTxt())
                    .build());
        }
        reviewList.forEach(review -> {
            reviewResponseDtos.add(ReviewResponseDto.builder()
                    .reviewContent(review.getReviewContent())
                    .reviewScore(review.getReviewScore())
                    .reviewTitle(review.getReviewTitle())
                    .regDate(review.getCreateDate())
                    .userLoginId(review.getUser().getUserId())
                    .outputReviewImgDtos(outputReviewImgDtos)
                    .build());
        });

        return reviewResponseDtos;
    }
}
