package com.spharosacademy.project.SSGBack.qna.controller;

import com.spharosacademy.project.SSGBack.qna.dto.QnaInputDto;
import com.spharosacademy.project.SSGBack.qna.dto.QnaOutputDto;
import com.spharosacademy.project.SSGBack.qna.entity.Qna;
import com.spharosacademy.project.SSGBack.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
@CrossOrigin

public class QnaController {
    private final QnaService qnaService;

    //문의 등록
    @PostMapping("/add")
    public String addQna(@RequestBody QnaInputDto qnaInputDto, Model model) {

        qnaService.addQna(qnaInputDto);
        model.addAttribute("message", "글작성이 완료 되었습니다");
        return "message";
//        public Qna addQna(@RequestBody RequestQnaDto requestQnaDto, Model model) {
//        return qnaService.addQna(requestQnaDto);
    }

    //전체 Q&A 목록 조회
    @GetMapping("/all")
    public List<Qna> getAll() {
        return qnaService.getAll();
    }


    // 문의 수정
    @PutMapping("/edit")
    public String editQna(@RequestBody QnaOutputDto qnaOutputDto, Model model) throws Exception {
        qnaService.editQnaById(qnaOutputDto);

        model.addAttribute("message", "글 수정이 완료 되었습니다");

        return "message";
    }
//    public Qna editQna
//    (@RequestBody ResponseQnaDto responseQnaDto) throws Exception {
//        return qnaService.editQnaById(responseQnaDto);


    //문의 삭제
    @DeleteMapping("/delete/{qnaId}")
    public void deleteQnaById(@PathVariable int qnaId, Model model ) throws Exception {
        qnaService.deleteQnaById(qnaId);

        model.addAttribute("message", "글 삭제가 완료 되었습니다");

    }

    // 특정 게시물 보기
    @GetMapping("/{productid}")
    public List<QnaOutputDto> getQnaByProductId(@PathVariable Long productid) {
        return qnaService.getQnaByProductId(productid);
    }


}
