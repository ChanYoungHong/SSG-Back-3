package com.spharosacademy.project.SSGBack.Answer.service.impl;

import com.spharosacademy.project.SSGBack.Answer.entity.Answer;
import com.spharosacademy.project.SSGBack.Answer.dto.AnswerDto;
import com.spharosacademy.project.SSGBack.Answer.repo.AnswerRepo;
import com.spharosacademy.project.SSGBack.Answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AnswerServiceImpl implements AnswerService {

    public final AnswerRepo answerRepo;

    @Override
    public Answer addAnswer(AnswerDto answerDto) {
        return answerRepo.save(Answer.builder()
                        .answerId(answerDto.getAnswerId())
                        .answerTitle(answerDto.getAnswerTitle())
                        .answerContent(answerDto.getAnswerContent())
                        .answerReg(answerDto.getAnswerReg())
                .build());
    }

    @Override
    public List<Answer> getAll() {
        return answerRepo.findAll();
    }
}
