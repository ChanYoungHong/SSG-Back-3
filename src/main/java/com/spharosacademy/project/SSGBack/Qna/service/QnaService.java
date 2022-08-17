package com.spharosacademy.project.SSGBack.Qna.service;


import com.spharosacademy.project.SSGBack.Qna.dto.ResponseQnaDto;
import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.RequestQnaDto;
import com.spharosacademy.project.SSGBack.review.dto.RequestReviewDto;

import java.util.List;

public interface QnaService {


    // 제품에 질문 추가
    Qna addQna(RequestQnaDto requestQnaDto);
//    Long addQna(RequestQnaDto requestQnaDto);

    //전체 질문 조회하기
    List<Qna> getAll();
    // ** 제품당 모든 Qna 를 가져온다
//    List <RequestQnaDto> getListOfQna(Long productId);


    // 질문 수정 하기
//    void editQna(RequestQnaDto requestQnaDto );
    Qna editQnaById(ResponseQnaDto responseQnaDto) throws Exception;
//    void editQna(ResponseQnaDto responseQnaDto);

    // 질문 삭제 하기
//    void deleteQna(Integer qnaId);
    void deleteQnaById(int qnaId) throws Exception;


}







    //이거는 왜 하는거지요~~?
//    default Qna dtoToEntity(RequestQnaDto productQnaDto) {
//        Qna productQna = Qna.builder()
//                .qnaTitle(productQnaDto.getQnaTitle())
//                .qnaContent(productQnaDto.getQnaContent())
//                .qnaTitle(productQnaDto.getQnaTitle())
//                .isSecret(productQnaDto.getIsSecret())
////type,비밀글 추가
//                .build();
//        return productQna;
//    }
//
//    default RequestQnaDto entityToDto(Qna productQna) {
//        RequestQnaDto requestQnaDto = RequestQnaDto.builder()
//                .qnaTitle(productQna.getQnaTitle())
//                .qnaContent(productQna.getQnaContent())
//                .qnaId(productQna.getQnaId())
//                .createDate(productQna.getCreateDate())
//                .updateDate(productQna.getUpdatedDate())
//                .build();
//        return requestQnaDto;
//    }
//}
