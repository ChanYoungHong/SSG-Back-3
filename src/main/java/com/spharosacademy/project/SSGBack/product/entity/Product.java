package com.spharosacademy.project.SSGBack.product.entity;

import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String color;
    private String brand;
    private int cnt;
    private int sellAmt;
    private String titleImgUrl;
    private String titleImgTxt;
    private String explanation;

}
