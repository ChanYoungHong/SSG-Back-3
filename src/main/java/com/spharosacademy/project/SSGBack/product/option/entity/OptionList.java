package com.spharosacademy.project.SSGBack.product.option.entity;

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
public class OptionList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ColorOption colorOption;

    @ManyToOne
    private SizeOption sizeOption;

    @ManyToOne
    private Product product;
}
