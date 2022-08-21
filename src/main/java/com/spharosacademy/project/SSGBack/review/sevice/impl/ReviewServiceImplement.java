package com.spharosacademy.project.SSGBack.review.sevice.impl;

import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.review.dto.input.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.input.UpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.OutputReviewImgDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ProductReviewResponseDto;
import com.spharosacademy.project.SSGBack.review.dto.output.UserReviewResponseDto;
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
    private final OptionRepository optionRepository;

    @Override
    public void addReview(ReviewInputDto reviewInputDto) {

        OrderDetail orderDetail = orderDetailRepository.findById(reviewInputDto.getOrderDetailId())
                .orElseThrow(NotOrderProductException::new);
        Product product = productRepository.findById(orderDetail.getProduct().getId())
                .orElseThrow(ProductNotFoundException::new);
        User user = iUserRepository.findById(orderDetail.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .user(user)
                .orderDetailId(orderDetail.getId())
                .reviewContent(reviewInputDto.getReviewContent())
                .reviewScore(reviewInputDto.getReviewScore())
                .reviewTitle(reviewInputDto.getReviewTitle())
                .build());

        reviewInputDto.getInputReviewImageDtos().forEach(inputReviewImageDto ->
                reviewImageRepository.save(ReviewImage.builder()
                .reviewImgTxt(inputReviewImageDto.getReviewImgTxt())
                .reviewImgUrl(inputReviewImageDto.getReviewImgUrl())
                .review(review)
                .build()));
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ProductReviewResponseDto> getAllByProductId(Long productId) {
        List<Review> reviewList = reviewRepository.findAllByProductId(productId);
        List<ProductReviewResponseDto> productReviewResponseDtos = new ArrayList<>();

        reviewList.forEach(review -> {
            List<OutputReviewImgDto> outputReviewImgDtos = new ArrayList<>();
            List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReviewId(review.getId());
            for (ReviewImage reviewImage : reviewImageList) {
                outputReviewImgDtos.add(OutputReviewImgDto.builder()
                        .reviewImgUrl(reviewImage.getReviewImgUrl())
                        .reviewImgTxt(reviewImage.getReviewImgTxt())
                        .build());
            }

            OrderDetail detail = orderDetailRepository.findById(review.getOrderDetailId()).get();
            OptionList optionList = optionRepository.findById(detail.getOptionId()).get();
            productReviewResponseDtos.add(ProductReviewResponseDto.builder()
                    .reviewId(review.getId())
                    .orderDetailId(detail.getId())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .userLoginId(review.getUser().getUserId())
                    .reviewScore(review.getReviewScore())
                    .regDate(review.getCreateDate())
                    .color(optionList.getColors().getName())
                    .size(optionList.getSize().getType())
                    .outputReviewImgDtos(outputReviewImgDtos)
                    .build());

        });

        return productReviewResponseDtos;
    }

    @Override
    public List<UserReviewResponseDto> getReviewByuserId(Long userId) {
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        List<UserReviewResponseDto> userReviewResponseDtos = new ArrayList<>();

        reviewList.forEach(
                review -> {
                    List<OutputReviewImgDto> outputReviewImgDtos = new ArrayList<>();
                    List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReviewId(review.getId());
                    for (ReviewImage reviewImage : reviewImageList) {
                        outputReviewImgDtos.add(OutputReviewImgDto.builder()
                                .reviewImgUrl(reviewImage.getReviewImgUrl())
                                .reviewImgTxt(reviewImage.getReviewImgTxt())
                                .build());
                    }

                    OrderDetail detail = orderDetailRepository.findById(review.getOrderDetailId()).get();
                    OptionList optionList = optionRepository.findById(detail.getOptionId()).get();

                    userReviewResponseDtos.add(UserReviewResponseDto.builder()
                            .reviewId(review.getId())
                            .orderDetailId(detail.getId())
                            .productId(review.getProduct().getId())
                            .productName(review.getProduct().getName())
                            .reviewContent(review.getReviewContent())
                            .reviewScore(review.getReviewScore())
                            .reviewTitle(review.getReviewTitle())
                            .color(optionList.getColors().getName())
                            .size(optionList.getSize().getType())
                            .regDate(review.getCreateDate())
                            .userLoginId(review.getUser().getUserId())
                            .outputReviewImgDtos(outputReviewImgDtos)
                            .build());

                });


        return userReviewResponseDtos;
    }

    @Override
    public Review editReviewById(UpdateReviewDto updateReviewDto) {
        User user = iUserRepository.findById(updateReviewDto.getMemberId()).get();
        Product product = productRepository.findById(updateReviewDto.getProductId()).get();

        Review review = reviewRepository.save(Review.builder()
                .reviewTitle(updateReviewDto.getReviewTitle())
                .orderDetailId(updateReviewDto.getOrderDetailId())
                .reviewContent(updateReviewDto.getReviewContent())
                .reviewScore(updateReviewDto.getReviewScore())
                .id(updateReviewDto.getReviewId())
                .user(user)
                .product(product)
                .build());

        updateReviewDto.getUpdateReviewImgDtos().forEach(updateReviewImgDto ->
                reviewImageRepository.save(ReviewImage.builder()
                .id(updateReviewImgDto.getReviewImgId())
                .reviewImgUrl(updateReviewImgDto.getReviewImgUrl())
                .reviewImgTxt(updateReviewImgDto.getReviewImgTxt())
                .review(review)
                .build()));

        return null;
    }

}
