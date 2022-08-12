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
    @JoinColumn(name = "colorOption_id")
    private ColorOption colorOption;

    @ManyToOne
    @JoinColumn(name = "sizeOption_id")
    private SizeOption sizeOption;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
