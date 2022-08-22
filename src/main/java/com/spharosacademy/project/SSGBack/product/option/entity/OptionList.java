package com.spharosacademy.project.SSGBack.product.option.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spharosacademy.project.SSGBack.product.entity.Product;
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
public class OptionList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float stock;

    @ManyToOne
    @JsonBackReference
    Product product;

    @ManyToOne
    private Colors colors;

    @ManyToOne
    private Size size;
}
