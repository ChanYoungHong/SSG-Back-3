package com.spharosacademy.project.SSGBack.Qna.service;


import com.spharosacademy.project.SSGBack.Qna.dto.ResponseQnaDto;
import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.RequestQnaDto;

import java.util.List;

public interface QnaService {


    // 질문하기
    Qna addQna(RequestQnaDto requestQnaDto);

    //전체 질문 조회하기
    List<Qna> getAll();

    // 질문 수정 하기
    Qna editQnaById(ResponseQnaDto responseQnaDto) throws Exception;

    // 질문 삭제 하기
    void deleteQnaById(int qnaId) throws Exception;


}
