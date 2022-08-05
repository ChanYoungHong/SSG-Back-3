package com.spharosacademy.project.SSGBack.Qna.service;


import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.QnaDto;

import java.util.List;

public interface QnaService {

    Qna addQna(QnaDto qnaDto);
    List<Qna> getAll();
}
