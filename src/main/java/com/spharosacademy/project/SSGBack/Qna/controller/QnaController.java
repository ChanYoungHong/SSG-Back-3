package com.spharosacademy.project.SSGBack.Qna.controller;

import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.QnaDto;
import com.spharosacademy.project.SSGBack.Qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor

public class QnaController {
    private final QnaService qnaService;

    //문의 등록
    @PostMapping("/add")
    public Qna addQna(@RequestBody QnaDto qnaDto) {
        return qnaService.addQna(qnaDto);
    }

    //전체 Q&A 목록 조회
    @GetMapping("/all")
    public List<Qna> getAll() {
        return qnaService.getAll();
    }


    //문의 수정
    @PutMapping("/edit")
    public ResponseEntity<Void> qnaEdit(@Valid @RequestBody QnaDto qnaDto {
        qnaService.Editqan()

    }

    //문의 삭제
    @DeleteMapping("/delete/{qnaId}")
    public ResponseEntity<Void> qnaDelete(@PathVariable int qnaId) {
        qnaService.deleteQna(qnaId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType((MediaType.APPLICATION_JSON)
                        .build();
    }



}
