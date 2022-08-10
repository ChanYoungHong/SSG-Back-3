package com.spharosacademy.project.SSGBack.category.entity;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CategorySS categorySS;

    @ManyToOne
    private CategoryS categoryS;

    @ManyToOne
    private CategoryM categoryM;

    @ManyToOne
    private CategoryL categoryL;

    @ManyToOne
    private Product product;

}
