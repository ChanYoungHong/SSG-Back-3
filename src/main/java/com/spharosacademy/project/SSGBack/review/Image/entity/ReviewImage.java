package com.spharosacademy.project.SSGBack.review.Image.entity;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewImgId;
    private String reviewImgUrl;


    @ManyToOne
    private Review review;

}

