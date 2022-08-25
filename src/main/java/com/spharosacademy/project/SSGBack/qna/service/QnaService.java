package com.spharosacademy.project.SSGBack.qna.service;

import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestDeleteQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestUpdateQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.output.ResponseProductQnaDto;
import com.spharosacademy.project.SSGBack.qna.entity.QnA;

import java.util.List;

public interface QnaService {

    void addQna(RequestQnaDto requestQnaDto, Long userId);

    List<ResponseProductQnaDto> getQnaByProductId(Long productId);

    QnA editQna(RequestUpdateQnaDto requestUpdateQnaDto, Long userId);

    void deleteQna(RequestDeleteQnaDto requestDeleteQnaDto, Long userId);
}
