package com.spharosacademy.project.SSGBack.Qna.controller;

import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.QnaDto;
import com.spharosacademy.project.SSGBack.Qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor

public class QnaController {
    private final QnaService qnaService;

    @PostMapping("/add")
    public Qna addQna(@RequestBody QnaDto qnaDto) {
        return qnaService.addQna(qnaDto);
    }

    @GetMapping("/getAll")
    public List<Qna> getAll() {
        return qnaService.getAll();
    }


}
