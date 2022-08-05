package com.spharosacademy.project.SSGBack.Answer.service;

import com.spharosacademy.project.SSGBack.Answer.entity.Answer;
import com.spharosacademy.project.SSGBack.Answer.dto.AnswerDto;

import java.util.List;

public interface AnswerService {

    Answer addAnswer(AnswerDto answerDto);
    List<Answer> getAll();

}
