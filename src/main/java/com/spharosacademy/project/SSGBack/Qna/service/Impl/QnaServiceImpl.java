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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        Product product = productRepository.findbyId(requestDto.get
//                .findById(createRequest.getOrderProductNo())
//                .orElseThrow(OrderProductNotFoundException::new);


        Qna qna = qnaRepo.save(Qna.builder()
                .qnaId(requestQnaDto.getQnaId())
                .qnaTitle(requestQnaDto.getQnaTitle())
                .qnaContent(requestQnaDto.getQnaContent())
                .isSecret(requestQnaDto.getIsSecret())
                .qnaType(requestQnaDto.getQnaType())
                .build());

        return qna;
    }

    // 질문 조회하기

    @Override
    public List<Qna> getAll() {
        List<Qna> ListQna= qnaRepo.findAll();
        return ListQna;
    }

    @Override
    public List<ResponseQnaDto> getQnaByProductId(Integer qnaId) {
        List<Qna> qnas = qnaRepo.findByProductId(qnaId);
        List<ResponseQnaDto> responseQnaDtos = new ArrayList<>();

        for(Qna qna : qnas) {
            responseQnaDtos.add(ResponseQnaDto.builder()
                    .qnaType(qna.getQnaType())
                    .qnaTitle(qna.getQnaTitle())
                    .qnaContent(qna.getQnaContent())
                    .build());
        }
        return responseQnaDtos;
    }

    //리스트 형식 제품아이디로 찾아서 가져와야함
    //상품을 조회할 때, 같이 갯수가 나간다.
    //



    //질문 수정하기
    @Override
    public Qna editQnaById(ResponseQnaDto responseQnaDto) throws Exception {
        Qna qna = qnaRepo.findById(responseQnaDto.getQnaId())
                .orElseThrow(QnaNotFoundException::new);
        qnaRepo.save(
                qna.builder()
                        .qnaId(responseQnaDto.getQnaId())
                        .qnaTitle(responseQnaDto.getQnaTitle())
                        .qnaContent(responseQnaDto.getQnaContent())
                        .isSecret(responseQnaDto.getIsSecret())
                        .qnaType(responseQnaDto.getQnaType())
                        .build());
        return qna;
    }


//    @Override
//    public Qna editQnaQyId(ResponseQnaDto responseQnaDto) {
//        Optional<Qna> result = qnaRepo.findById(responseQnaDto.getQnaId());
//        if(result.isPresent()) {
//            Qna entity = result.get();
//
//            entity.changeQnaTitle(responseQnaDto.getQnaTitle());
//            entity.changeQnaContent(responseQnaDto.getQnaContent());
//
//            qnaRepo.save(entity);
//        }
//        Qna qna = qnaRepo.findById(responseQnaDto.getQnaId()).get();
//        qnaRepo.save(Qna.builder()
//                        .qnaId(responseQnaDto.getQnaId())
//                        .qnaTitle(responseQnaDto.getQnaTitle())
//                        .qnaContent(responseQnaDto.getQnaContent())
//                        .isSecret(editQnaById())
//
//                .build());
//        return qna;
//    }

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



    // 특정 질문만 보기
//    @Override
//    public void findQnaById(int qnaId) throws Exception {
//        Optional<Qna> findByQnaId = qnaRepo.findById(qnaId);
//        if (findByQnaId.isPresent()) {
//            qnaRepo.findById(qnaId);
//        }else {
//            throw new Exception();
//        }
//    }


}
