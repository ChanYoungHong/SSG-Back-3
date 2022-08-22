package com.spharosacademy.project.SSGBack.qna.repository;

import com.spharosacademy.project.SSGBack.qna.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<QnA, Long> {

    List<QnA> findAllByProductId(Long productId);

    Long countByProductId(Long productId);

    List<QnA> findAllByUserId(Long userId);
}
