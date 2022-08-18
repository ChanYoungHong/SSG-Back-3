package com.spharosacademy.project.SSGBack.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private int cnt;
    private int sellAmt;
    private String explanation;
    private String thumbnailUrl;

    @ManyToOne
    CategorySS categorySS;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<OptionList> optionLists = new ArrayList<>();
}
