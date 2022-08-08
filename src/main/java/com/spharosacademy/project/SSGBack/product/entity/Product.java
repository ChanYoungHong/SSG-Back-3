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

    private String titleImgUrl;

    private String detailImgUrl;

    @OneToOne
    private CategorySS categorySS;

}
