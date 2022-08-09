package com.spharosacademy.project.SSGBack.product.entity;

import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name")
    private String name;

    @Column(name = "product_price")
    private int price;

    @Column(name="product_color")
    private String color;

    @Column(name="product_brand")
    private String brand;

    @Column(name="product_cnt")
    private int cnt;

    @Column(name="product_sell_amt")
    private int sellAmt;

    private String titleImgUrl;
    private String titleImgTxt;
    private String explanation;

    @OneToOne
    private CategorySS categorySS;



}
