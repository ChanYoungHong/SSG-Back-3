package com.spharosacademy.project.SSGBack.product.Image.entity;


import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class ProductDetailImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String imageUrl;
    private String Txt;

    @ManyToOne
    private Product product;
}
