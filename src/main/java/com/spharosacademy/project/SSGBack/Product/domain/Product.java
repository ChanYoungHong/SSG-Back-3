package com.spharosacademy.project.SSGBack.Product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "product")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_color")
    private String productColor;

    @Column(name="product_brand")
    private String productBrand;

    @Column(name="product_cnt")
    private int productCnt;

    @Column(name="product_sell_amt")
    private int productSellAmt;


    @ManyToOne
    @JoinColumn(name= "category_s_id")
    private CategoryS categoryS;
}
