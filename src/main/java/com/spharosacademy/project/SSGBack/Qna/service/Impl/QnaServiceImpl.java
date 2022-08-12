package com.spharosacademy.project.SSGBack.Qna.service.Impl;

import com.spharosacademy.project.SSGBack.Qna.dto.ResponseQnaDto;
import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.RequestQnaDto;
import com.spharosacademy.project.SSGBack.Qna.execption.QnaNotFoundException;
import com.spharosacademy.project.SSGBack.Qna.repository.QnaRepo;
import com.spharosacademy.project.SSGBack.Qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class QnaServiceImpl implements QnaService {

    private final QnaRepo qnaRepo;

//    @Override
//    public Long addQna(RequestQnaDto requestQnaDto) {
//        Optional<Qna> result = dtoToEntity(requestQnaDto);
//        qnaRepo.save(productQna)
//
//
//
//
//        return  ;
//    }
//
//    //조회하기
//    @Override
//    public List<RequestQnaDto> getListOfQna(Long productId) {
//
//        Product product = Product.builder().productId(productId).build();
//        List<Qna> result = qnaRepo.findByProduct(product);
//        return result.stream().map(qna -> entityToDto(qna)).collect(Collectors.toList());
//    }
//
//    @Override
//    public void editQna(RequestQnaDto requestQnaDto) {
//        Qna qna = qnaRepo.findById(requestQnaDto.getQnaId())
//                .orElseThrow(QnaNotFoundException::new);
//
//        qna.
//
//    }
//
//    @Override
//    public void deleteQna(Integer qnaId) {
//
//    }
//}
//
//
//

// 질문작성하기 서비스
    @Override
    public Qna addQna(RequestQnaDto requestQnaDto) {
        Qna qna = qnaRepo.save(Qna.builder()
                .qnaId(requestQnaDto.getQnaId())
                .qnaTitle(requestQnaDto.getQnaTitle())
                .qnaContent(requestQnaDto.getQnaContent())
                .build());

        return qna;
    }

    // 질문 조회하기

    @Override
    public List<Qna> getAll() {
        List<Qna> ListQna = qnaRepo.findAll();
        return ListQna;
    }

    //질문 수정하기
    @Override
    public Qna editQnaById(ResponseQnaDto responseQnaDto) throws Exception {
        Qna qna = qnaRepo.findById(responseQnaDto.getQnaId()).get();
        qnaRepo.save(Qna.builder()
                        .qnaId(responseQnaDto.getQnaId())
                        .qnaTitle(responseQnaDto.getQnaTitle())
                        .qnaContent(responseQnaDto.getQnaContent())
//                        .isSecret(editQnaById())

                .build());
        return qna;
    }

    // 질문 삭제하기
    @Override
    public void deleteQnaById(int qnaId) throws Exception {
        Optional<Qna> deleteById = qnaRepo.findById(qnaId);
        if (deleteById.isPresent()) {
            qnaRepo.deleteById(qnaId);
        } else {
            throw new Exception();
        }

    }

}
