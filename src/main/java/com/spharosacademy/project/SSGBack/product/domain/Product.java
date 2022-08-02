package com.spharosacademy.project.SSGBack.product.domain;

import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name = "product_price")
    private int price;

    @Column(name="product_color")
    private String productColor;

    @Column(name="product_brand")
    private String productBrand;

    @Column(name="product_cnt")
    private int productCnt;

    @Column(name="product_sell_amt")
    private int productSellAmt;

    @OneToOne
    private CategoryS categoryS;

}
