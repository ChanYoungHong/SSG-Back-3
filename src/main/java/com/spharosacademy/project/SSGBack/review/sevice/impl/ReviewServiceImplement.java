package com.spharosacademy.project.SSGBack.review.sevice.impl;

import com.spharosacademy.project.SSGBack.order.exception.OrderIdNotFound;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import com.spharosacademy.project.SSGBack.orderlist.repo.OrderListRepository;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDeleteDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.input.RequestUpdateReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.OutputReviewImgDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseUserReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.exception.AlreadyExistReviewException;
import com.spharosacademy.project.SSGBack.review.exception.NotOrderProductException;
import com.spharosacademy.project.SSGBack.review.image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.image.repo.ReviewImageRepository;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepository;
import com.spharosacademy.project.SSGBack.review.sevice.ReviewService;
import com.spharosacademy.project.SSGBack.s3.dto.ReviewImageS3Dto;
import com.spharosacademy.project.SSGBack.s3.service.S3UploaderService;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final OrderListRepository orderListRepository;
    private final OptionRepository optionRepository;
    private final S3UploaderService s3UploaderService;

    @Override
    @Transactional(rollbackFor = {NullPointerException.class, IllegalArgumentException.class})
    public void addReview(RequestReviewDto requestReviewDto, List<MultipartFile> multipartFileList, Long userId) {

        OrderList orderList = orderListRepository.findById(requestReviewDto.getOrderDetailId())
                .orElseThrow(NotOrderProductException::new);
        Product product = productRepository.findById(orderList.getProduct().getId())
                .orElseThrow(ProductNotFoundException::new);
        User user = userRepository.findById(orderList.getMemberId())
                .orElseThrow(UserNotFoundException::new);

        List<Long> userReview = reviewRepository.getOrderId(userId);

        List<Long> orderLists = orderListRepository.getOrderId(userId);
        if (!orderLists.contains(requestReviewDto.getOrderDetailId())) {
            throw new OrderIdNotFound();
        }
        if (userReview.contains(requestReviewDto.getOrderDetailId())) {
            throw new AlreadyExistReviewException();
        }

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .user(user)
                .orderDetailId(requestReviewDto.getOrderDetailId())
                .reviewContent(requestReviewDto.getReviewContent())
                .reviewScore(requestReviewDto.getReviewScore())
                .build());

//        ReviewImageS3Dto reviewImageS3Dto;
//        for (MultipartFile multipartFiles : multipartFileList) {
//            try {
//                reviewImageS3Dto = s3UploaderService.uploadReviewImage(multipartFiles, "myspharosbucket", "myDir");
//
//                reviewImageRepository.save(ReviewImage.builder()
//                        .review(review)
//                        .reviewImgUrl(reviewImageS3Dto.getImageUrl())
//                        .reviewImgTxt(reviewImageS3Dto.getSaveFileName())
//                        .productId(product.getId())
//                        .build());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    @Override
    public void deleteReviewById(RequestReviewDeleteDto requestReviewDeleteDto, Long userId) {
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

            OrderList detail = orderListRepository.findById(review.getOrderDetailId()).orElseThrow(OrderIdNotFound::new);
            OptionList optionList = optionRepository.findById(detail.getOptionId()).orElseThrow(OptionNotFoundException::new);
            responseProductReviewDtos.add(ResponseProductReviewDto.builder()
                    .reviewId(review.getId())
                    .orderDetailId(review.getOrderDetailId())
                    .reviewContent(review.getReviewContent())
                    .userLoginId(review.getUser().getUserId())
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
    public void addImages(Long id, List<MultipartFile> multipartFileList) {
        Review review = reviewRepository.findById(id).get();

        ReviewImageS3Dto reviewImageS3Dto;
        for (MultipartFile multipartFiles : multipartFileList) {
            try {
                reviewImageS3Dto = s3UploaderService.uploadReviewImage(multipartFiles, "myspharosbucket", "myDir");

                reviewImageRepository.save(ReviewImage.builder()
                        .review(review)
                        .reviewImgUrl(reviewImageS3Dto.getImageUrl())
                        .reviewImgTxt(reviewImageS3Dto.getSaveFileName())
                        .productId(review.getProduct().getId())
                        .build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

                    OrderList detail = orderListRepository.findById(review.getOrderDetailId()).get();
                    OptionList optionList = optionRepository.findById(detail.getOptionId()).get();

                    responseUserReviewDtos.add(ResponseUserReviewDto.builder()
                            .reviewId(review.getId())
                            .orderDetailId(review.getOrderDetailId())
                            .productId(review.getProduct().getId())
                            .productName(review.getProduct().getName())
                            .reviewContent(review.getReviewContent())
                            .reviewScore(review.getReviewScore())
                            .color(optionList.getColors().getName())
                            .size(optionList.getSize().getType())
                            .regDate(review.getCreateDate())
                            .updateDate(review.getUpdatedDate())
                            .userLoginId(review.getUser().getUserId())
                            .outputReviewImgDtos(outputReviewImgDtos)
                            .build());

                });


        return responseUserReviewDtos;
    }

    @Override
    public Review editReviewById(RequestUpdateReviewDto requestUpdateReviewDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(requestUpdateReviewDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        Review review = reviewRepository.save(Review.builder()
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
