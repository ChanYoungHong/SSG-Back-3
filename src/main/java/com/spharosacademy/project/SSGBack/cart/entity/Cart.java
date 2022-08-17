package com.spharosacademy.project.SSGBack.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.transaction.Transactional;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Transactional
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float qty;
    private Long optionId;

    @Column(nullable = true)
    private Long colorId;

    @Column(nullable = true)
    private Long sizeId;
    
    @ManyToOne
    private User user;

    @ManyToOne
    @JsonBackReference
    private Product product;
}
