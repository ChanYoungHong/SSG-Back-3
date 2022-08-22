package com.spharosacademy.project.SSGBack.category.entity;

import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CategoryS")
public class CategoryS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    private CategoryM categoryM;


}
