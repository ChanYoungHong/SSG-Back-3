package com.spharosacademy.project.SSGBack.Qna.repository;

import com.spharosacademy.project.SSGBack.Qna.entity.Qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepo extends JpaRepository<Qna, Integer> {
    
}
