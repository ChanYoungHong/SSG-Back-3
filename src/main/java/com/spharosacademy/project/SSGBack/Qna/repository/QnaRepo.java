package com.spharosacademy.project.SSGBack.Qna.repository;

import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepo extends JpaRepository<Qna, Integer> {

    List<Qna>findByProduct(Product product);
}
