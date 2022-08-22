package com.spharosacademy.project.SSGBack;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name="product_name")
    private String name;

    @Column(name = "product_price")
    private Long price;

    @Column(name="product_color")
    private String color;

    @Column(name="product_brand")
    private String brand;

    @Column(name="product_cnt")
    private int cnt;

    private String size;

    @Column(name="product_sell_amt")
    private int sellAmt;

    private String titleImgUrl;
    private String titleImgTxt;
    private String explanation;

}
