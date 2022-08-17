package com.spharosacademy.project.SSGBack.Qna.controller;

import com.spharosacademy.project.SSGBack.Qna.dto.ResponseQnaDto;
import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.RequestQnaDto;
import com.spharosacademy.project.SSGBack.Qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
@CrossOrigin

public class QnaController {
    private final QnaService qnaService;

    //문의 등록
    @PostMapping("/add")
    public Qna addQna(@RequestBody RequestQnaDto requestQnaDto) {
        return qnaService.addQna(requestQnaDto);
    }

    //전체 Q&A 목록 조회
    @GetMapping("/all")
    public List<Qna> getAll() {
        return qnaService.getAll();
    }


    //특정 문의 수정
    @PutMapping("/edit")
    public Qna editQna
    (@RequestBody ResponseQnaDto responseQnaDto) throws Exception {
        return qnaService.editQnaById(responseQnaDto);
    }

    //문의 삭제
    @DeleteMapping("/delete/{qnaId}")
    public void deleteQnaById(@PathVariable int qnaId ) throws Exception {
        qnaService.deleteQnaById(qnaId);

    }

}
