package com.spharosacademy.project.SSGBack.Product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CategoryL")


public class CategoryL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_l_id")
    private int categoryLId;

    @Column(name = "category_l_name")
    private String categoryLName;

}
