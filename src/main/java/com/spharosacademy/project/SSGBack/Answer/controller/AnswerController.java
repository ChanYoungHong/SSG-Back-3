package com.spharosacademy.project.SSGBack.Answer.controller;

import com.spharosacademy.project.SSGBack.Answer.entity.Answer;
import com.spharosacademy.project.SSGBack.Answer.dto.AnswerDto;
import com.spharosacademy.project.SSGBack.Answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor

public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/add")
    public Answer addAnswer(@RequestBody AnswerDto answerDto) {
        return answerService.addAnswer(answerDto);
    }

    @GetMapping("/getAll")
    public List<Answer> getAll() {
        return answerService.getAll();
    }


}
