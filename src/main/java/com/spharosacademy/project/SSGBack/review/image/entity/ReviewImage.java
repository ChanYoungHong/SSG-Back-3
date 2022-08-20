package com.spharosacademy.project.SSGBack.review.image.entity;

import com.spharosacademy.project.SSGBack.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewImgUrl;
    private String reviewImgTxt;

    @ManyToOne
    Review review;
}
