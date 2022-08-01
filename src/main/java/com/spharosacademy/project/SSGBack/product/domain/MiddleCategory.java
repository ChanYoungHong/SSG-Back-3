package com.spharosacademy.project.SSGBack.product.domain;

import javax.persistence.*;

@Entity
public class MiddleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    private Category category;

}
