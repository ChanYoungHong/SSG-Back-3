package com.spharosacademy.project.SSGBack.product.entity;

import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
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
    private String mallText;
    private float oldPrice;
    private float newPrice;
    private float discountRate;
    private String priceText;
    private String brand;
    private int sellAmt;
    private String explanation;
    private String thumbnailUrl;

    @ManyToOne
    CategorySS categorySS;

}
