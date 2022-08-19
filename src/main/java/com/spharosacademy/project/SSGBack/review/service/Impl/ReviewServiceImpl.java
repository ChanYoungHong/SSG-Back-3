package com.spharosacademy.project.SSGBack.review.service.Impl;



import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.ColorRepository;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.option.repository.SizeRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.qna.dto.QnaInputDto;
import com.spharosacademy.project.SSGBack.qna.dto.QnaOutputDto;
import com.spharosacademy.project.SSGBack.qna.entity.Qna;
import com.spharosacademy.project.SSGBack.review.Image.repo.ReviewImageRepo;
import com.spharosacademy.project.SSGBack.review.dto.ReviewInputDto;
import com.spharosacademy.project.SSGBack.review.dto.ReviewOutputDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepo;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ProductRepository productRepository;
    private final ReviewImageRepo reviewImageRepo;

    private final OptionRepository optionRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;


@Override
    public Review addReview(ReviewInputDto reviewInputDto, MultipartFile file) throws Exception {

//    String projectPath = System.getProperty("user.dir") + " \\src\\main\\resources\\static\\files";
//
//    UUID uuid = UUID.randomUUID();
//    String fileName = uuid + "_" + file.getOriginalFilename();
//    File saveFile = new File(projectPath, fileName);
//    file.transferTo(saveFile);
//    reviewInputDto.setFilename(fileName);
//    reviewInputDto.setFilepath("/files/" + fileName);


    Product product = productRepository.findById(reviewInputDto.getProductid()).get();
    Review review = reviewRepo.save(Review.builder()
            .reviewId(reviewInputDto.getReviewId())
            .product(product)
            .reviewAuthorId(reviewInputDto.getReviewAuthorId())
            .reviewContent(reviewInputDto.getReviewContent())
            .reviewScore(reviewInputDto.getReviewScore())
                    .filename(reviewInputDto.getFilename())
                    .filepath(reviewInputDto.getFilepath())
            .build());
    return review;
}

    // 리뷰 수정하기

    @Override
    public Review editReviewById(ReviewOutputDto reviewOutputDto) throws Exception {
        Review review = reviewRepo.findById(reviewOutputDto.getReviewId()).get();

        reviewRepo.save(Review.builder()
                .reviewId(reviewOutputDto.getReviewId())
                .reviewAuthorId(reviewOutputDto.getReviewAuthorId())
                .reviewContent(reviewOutputDto.getReviewContent())
                .reviewScore(reviewOutputDto.getReviewScore())

                .build());
        return review;
    }



    // 리뷰 삭제하기
    @Override
    public void deleteReviewById(Long reviewId) throws Exception {
        Optional<Review> deleteById = reviewRepo.findById(reviewId);
        if (deleteById.isPresent()) {
            reviewRepo.deleteById(reviewId);
        } else {
            throw new Exception();
        }

    }

    // 리뷰 전체 페이지 조회
    @Override
    public List<Review> getAll() {
        List<Review> ListReview = reviewRepo.findAll();
        return ListReview;
    }

    @Override
    public List<ReviewOutputDto> getReviewByProductId(Long productid) {
        List<Review> reviews = reviewRepo.findByProductId(productid);
        List<ReviewOutputDto> reviewOutputDtos = new ArrayList<>();

        for ( Review review : reviews ) {
            reviewOutputDtos.add(ReviewOutputDto.builder()
                    .reviewId(review.getReviewId())
                    .filename(review.getFilename())
                    .productid(review.getProduct().getId())
                    .productName(review.getProduct().getName())
                    .reviewScore(review.getReviewScore())
                    .createDate(review.getCreateDate())
                    .reviewContent(review.getReviewContent())
                    .count(reviewRepo.countByProductId(productid))
                    .build());
        }

        return reviewOutputDtos;
    }
}
