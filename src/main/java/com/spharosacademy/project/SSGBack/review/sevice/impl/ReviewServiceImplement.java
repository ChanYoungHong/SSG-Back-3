package com.spharosacademy.project.SSGBack.review.sevice.impl;

import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import com.spharosacademy.project.SSGBack.order.repository.OrderDetailRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDeleteDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestUpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.OutputReviewImgDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseUserReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
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
    public void addReview(RequestReviewDto requestReviewDto) {

        OrderDetail orderDetail = orderDetailRepository.findById(requestReviewDto.getOrderDetailId())
                .orElseThrow(NotOrderProductException::new);
        Product product = productRepository.findById(orderDetail.getProduct().getId())
                .orElseThrow(ProductNotFoundException::new);
        User user = iUserRepository.findById(orderDetail.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .user(user)
                .orderDetailId(orderDetail.getId())
                .reviewContent(requestReviewDto.getReviewContent())
                .reviewScore(requestReviewDto.getReviewScore())
                .reviewTitle(requestReviewDto.getReviewTitle())
                .build());

        requestReviewDto.getRequestReviewImageDtos().forEach(inputReviewImageDto ->
                reviewImageRepository.save(ReviewImage.builder()
                        .reviewImgTxt(inputReviewImageDto.getReviewImgTxt())
                        .reviewImgUrl(inputReviewImageDto.getReviewImgUrl())
                        .review(review)
                        .build()));
    }

    @Override
    public void deleteReviewById(RequestReviewDeleteDto requestReviewDeleteDto) {
        reviewRepository.deleteById(requestReviewDeleteDto.getReviewId());
    }

    @Override
    public List<ResponseProductReviewDto> getAllByProductId(Long productId) {
        List<Review> reviewList = reviewRepository.findAllByProductId(productId);
        List<ResponseProductReviewDto> responseProductReviewDtos = new ArrayList<>();

        reviewList.forEach(review -> {
            List<OutputReviewImgDto> outputReviewImgDtos = new ArrayList<>();
            List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReviewId(review.getId());
            for (ReviewImage reviewImage : reviewImageList) {
                outputReviewImgDtos.add(OutputReviewImgDto.builder()
                        .reviewImgUrl(reviewImage.getReviewImgUrl())
                        .reviewImgTxt(reviewImage.getReviewImgTxt())
                        .build());
            }
            ReviewTotalDto reviewTotalDto = reviewRepository.collectByProductId(productId);
            OrderDetail detail = orderDetailRepository.findById(review.getOrderDetailId()).get();
            OptionList optionList = optionRepository.findById(detail.getOptionId()).get();
            responseProductReviewDtos.add(ResponseProductReviewDto.builder()
                    .reviewId(review.getId())
                    .orderDetailId(detail.getId())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .userLoginId(review.getUser().getLoginId())
                    .reviewTotalDto(reviewTotalDto)
                    .regDate(review.getCreateDate())
                    .updateDate(review.getUpdatedDate())
                    .color(optionList.getColors().getName())
                    .size(optionList.getSize().getType())
                    .outputReviewImgDtos(outputReviewImgDtos)
                    .build());

        });

        return responseProductReviewDtos;
    }

    @Override
    public List<ResponseUserReviewDto> getReviewByUserId(Long userId) {
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        List<ResponseUserReviewDto> responseUserReviewDtos = new ArrayList<>();

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

                    responseUserReviewDtos.add(ResponseUserReviewDto.builder()
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
                            .updateDate(review.getUpdatedDate())
                            .userLoginId(review.getUser().getLoginId())
                            .outputReviewImgDtos(outputReviewImgDtos)
                            .build());

                });


        return responseUserReviewDtos;
    }

    @Override
    public Review editReviewById(RequestUpdateReviewDto requestUpdateReviewDto) {
        User user = iUserRepository.findById(requestUpdateReviewDto.getMemberId())
                .orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(requestUpdateReviewDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        Review review = reviewRepository.save(Review.builder()
                .reviewTitle(requestUpdateReviewDto.getReviewTitle())
                .orderDetailId(requestUpdateReviewDto.getOrderDetailId())
                .reviewContent(requestUpdateReviewDto.getReviewContent())
                .reviewScore(requestUpdateReviewDto.getReviewScore())
                .id(requestUpdateReviewDto.getReviewId())
                .user(user)
                .product(product)
                .build());

        requestUpdateReviewDto.getRequestUpdateReviewImgDtos().forEach(updateReviewImgDto ->
                reviewImageRepository.save(ReviewImage.builder()
                        .id(updateReviewImgDto.getReviewImgId())
                        .reviewImgUrl(updateReviewImgDto.getReviewImgUrl())
                        .reviewImgTxt(updateReviewImgDto.getReviewImgTxt())
                        .review(review)
                        .build()));

        return null;
    }

}
