package com.spharosacademy.project.SSGBack.qna.service;


import com.spharosacademy.project.SSGBack.qna.dto.QnaInputDto;
import com.spharosacademy.project.SSGBack.qna.dto.QnaOutputDto;
import com.spharosacademy.project.SSGBack.qna.entity.Qna;

import java.util.List;

public interface QnaService {


    // 제품에 질문 추가
    Qna addQna(QnaInputDto qnaInputDto);
//    Long addQna(RequestQnaDto requestQnaDto);

    //전체 질문 조회하기
    List<Qna> getAll();
    // ** 제품당 모든 Qna 를 가져온다
//    List <RequestQnaDto> getListOfQna(Long productId);

    //특정 질문 조회하기(productID 로 변경 필요)
    List<QnaOutputDto> getQnaByProductId(Long productid);


    // 질문 수정 하기
//    void editQna(RequestQnaDto requestQnaDto );
    Qna editQnaById(QnaOutputDto qnaOutputDto) throws Exception;
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
