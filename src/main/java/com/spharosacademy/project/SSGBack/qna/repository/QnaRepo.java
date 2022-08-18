package com.spharosacademy.project.SSGBack.qna.repository;

import com.spharosacademy.project.SSGBack.qna.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepo extends JpaRepository<Qna, Integer> {

    List<Qna> findByProductId(Long productId);

    long countByProductId(Long productid);
    
}
