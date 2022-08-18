package com.spharosacademy.project.SSGBack.review.service.Impl;



import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.ResponseReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepo;
import com.spharosacademy.project.SSGBack.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
//    private final ReviewImageRepo reviewImageRepo;


    //리뷰 사진 등록
    // 사진 등록 안하면?????

    //수정중


    //이미지 파일 추가 하려고 하는데 잘 안되는데.
//    public void addReview(RequestReviewDto requestReviewDto, MultipartFile file) throws Exception {
//
//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//
//        UUID uuid = UUID.randomUUID();
//        String fileName = uuid + "_" + file.getOriginalFilename();
//        File saveFile = new File(projectPath, fileName);
//        file.transferTo(saveFile);
//
//        Review review = reviewRepo.save(Review.builder()
//                        .reviewId(requestReviewDto.getReviewId())
//                        .reviewAuthorId(requestReviewDto.getReviewAuthorId())
//                        .reviewContent(requestReviewDto.getReviewContent())
//                        .reviewScore(requestReviewDto.getReviewScore())
//                        .filename(requestReviewDto.getFilename())
//                        .filepath(requestReviewDto.getFilepath())
//                        .build());
//    }


//    기존에 되었던 것
@Override
    public Review addReview(RequestReviewDto requestReviewDto) {
    Review review = reviewRepo.save(Review.builder()
            .reviewId(requestReviewDto.getReviewId())
            .reviewAuthorId(requestReviewDto.getReviewAuthorId())
            .reviewContent(requestReviewDto.getReviewContent())
            .reviewScore(requestReviewDto.getReviewScore())
            .build());
    return review;
}

   //안되던것
//        requestReviewDto.getReviewImageList().forEach(reviewImage -> {
//            reviewImageRepo.save(ReviewImage.builder()
//                            .imgUrl(reviewImage.getImgUrl())
//                            .review(review)
//                    .build());
//        });
 //       return review;
 //   }

    // 리뷰 수정하기

    @Override
    public Review editReviewById(ResponseReviewDto responseReviewDto) throws Exception {
        Review review = reviewRepo.findById(responseReviewDto.getReviewId()).get();

        reviewRepo.save(Review.builder()
                .reviewId(responseReviewDto.getReviewId())
                .reviewAuthorId(responseReviewDto.getReviewAuthorId())
                .reviewContent(responseReviewDto.getReviewContent())
                .reviewScore(responseReviewDto.getReviewScore())
//                .reviewModDate(responseReviewDto.getReviewModDate())
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
}