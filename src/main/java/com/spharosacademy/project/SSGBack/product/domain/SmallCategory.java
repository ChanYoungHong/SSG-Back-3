package com.spharosacademy.project.SSGBack.product.domain;

import javax.persistence.*;

@Entity
public class SmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    private MiddleCategory middleCategory;

}
