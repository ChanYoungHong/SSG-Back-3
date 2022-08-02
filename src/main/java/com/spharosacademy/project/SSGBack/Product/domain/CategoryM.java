package com.spharosacademy.project.SSGBack.Product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CategoryM")

public class CategoryM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_m_id")
    private int categoryMId;

    @Column(name = "category_m_name")
    private String categoryMName;

    @ManyToOne
    @JoinColumn(name = "category_l_id")
    private CategoryL categoryL;

}
