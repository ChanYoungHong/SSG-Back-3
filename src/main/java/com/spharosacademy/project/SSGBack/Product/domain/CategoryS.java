package com.spharosacademy.project.SSGBack.Product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CategoryS")
public class CategoryS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catergory_s_id")
    private int categorySId;

    @Column(name = "catergory_s_name")
    private String categorySName;

    @ManyToOne
    @JoinColumn(name = "category_m_id")
    private CategoryM categoryM;

}
