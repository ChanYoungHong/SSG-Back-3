package com.spharosacademy.project.SSGBack.order.entity;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Long optionId;
    private float qty;
    private float totalPrice;
    private Long userId;

    @ManyToOne
    Orders orders;

    @ManyToOne
    Product product;

}
