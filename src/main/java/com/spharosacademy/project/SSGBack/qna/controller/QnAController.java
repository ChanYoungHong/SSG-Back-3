package com.spharosacademy.project.SSGBack.qna.controller;

import com.spharosacademy.project.SSGBack.qna.dto.input.RequestDeleteQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestUpdateQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.output.ResponseProductQnaDto;
import com.spharosacademy.project.SSGBack.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnAController {

    private final QnaService qnaService;

    @PostMapping("/add")
    public String addQna(@RequestBody RequestQnaDto requestQnaDto){
        qnaService.addQna(requestQnaDto);
        return "문의가 등록되었습니다";
    }
    //특정 상품의 Qna 조회
    @GetMapping("/product/{productId}")
    public List<ResponseProductQnaDto> responseProductQnaDtos(@PathVariable Long productId){
        return qnaService.getQnaByProductId(productId);
    }

    @PutMapping("edit")
    public String updateQna(@RequestBody RequestUpdateQnaDto requestUpdateQnaDto){
        qnaService.editQna(requestUpdateQnaDto);
        return "문의가 수정되었습니다";
    }

    @DeleteMapping("/delete")
    public String deleteQna(@RequestBody RequestDeleteQnaDto requestDeleteQnaDto){
        qnaService.deleteQna(requestDeleteQnaDto);
        return "문의가 삭제되었습니다";
    }
}
