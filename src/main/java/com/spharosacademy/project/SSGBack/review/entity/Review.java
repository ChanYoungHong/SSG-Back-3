package com.spharosacademy.project.SSGBack.review.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.user.domain.User;
import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String reviewContent;
    private String reviewAuthorId;

    //별점 어떻게 구현하지?

    private int reviewScore;
    private String filename;
    private String filepath;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @OneToOne
    private OptionList optionList;



}