package com.spharosacademy.project.SSGBack.Qna.service;


import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.QnaDto;

import java.util.List;

public interface QnaService {


    // 질문하기
    Qna addQna(QnaDto qnaDto);

    //질문 조회하기
    List<Qna> getAll();
}
