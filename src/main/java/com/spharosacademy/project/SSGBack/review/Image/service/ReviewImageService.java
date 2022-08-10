package com.spharosacademy.project.SSGBack.review.Image.service;

import com.spharosacademy.project.SSGBack.review.Image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;

import java.math.BigInteger;
import java.util.List;

public interface ReviewImageService {

    List<ReviewImage> getReviewImageById(BigInteger id);
}
