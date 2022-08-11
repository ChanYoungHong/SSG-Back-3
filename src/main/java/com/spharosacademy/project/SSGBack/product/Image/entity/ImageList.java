package com.spharosacademy.project.SSGBack.product.Image.entity;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductTitleImage productTitleImage;

    @ManyToOne
    private ProductDetailImage productDetailImage;

    @ManyToOne
    private Product product;

}
