package com.spharosacademy.project.SSGBack.Answer.repo;

import com.spharosacademy.project.SSGBack.Answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {
}
