package com.spharosacademy.project.SSGBack.qna.service.Impl;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.qna.dto.QnaInputDto;
import com.spharosacademy.project.SSGBack.qna.dto.QnaOutputDto;
import com.spharosacademy.project.SSGBack.qna.entity.Qna;
import com.spharosacademy.project.SSGBack.qna.execption.QnaNotFoundException;
import com.spharosacademy.project.SSGBack.qna.repository.QnaRepo;
import com.spharosacademy.project.SSGBack.qna.service.QnaService;
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
    private final ProductRepository productRepository;


    // 질문작성하기 서비스

    @Override
    public Qna addQna(QnaInputDto qnaInputDto) {

        Product product = productRepository.findById(qnaInputDto.getProductid()).get();

        Qna qna = qnaRepo.save(Qna.builder()
                .qnaId(qnaInputDto.getQnaId())
                .qnaTitle(qnaInputDto.getQnaTitle())
                .qnaContent(qnaInputDto.getQnaContent())
                .isSecret(qnaInputDto.getIsSecret())
                .product(product)
                .qnaType(qnaInputDto.getQnaType())
                .build());

        return qna;
    }


    // 전체 질문 조회하기

    @Override
    public List<Qna> getAll() {
        List<Qna> ListQna = qnaRepo.findAll();
        return ListQna;
    }

    @Override
    public List<QnaOutputDto> getQnaByProductId(Long productid) {
        List<Qna> qnas = qnaRepo.findByProductId(productid);
        List<QnaOutputDto> qnaOutputDtos = new ArrayList<>();

        for (Qna qna : qnas) {
            qnaOutputDtos.add(QnaOutputDto.builder()
                    .qnaId(qna.getQnaId())
                    .titleImgUrl(qna.getProduct().getThumbnailUrl())
                    .productName(qna.getProduct().getName())
                    .productBrand(qna.getProduct().getBrand())
                    .productid(qna.getProduct().getId())
                    .qnaType(qna.getQnaType())
                    .qnaTitle(qna.getQnaTitle())
                    .qnaContent(qna.getQnaContent())
                    .isSecret(qna.getIsSecret())
                    .createDate(qna.getCreateDate())
                    .updateDate(qna.getUpdatedDate())
                    .count(qnaRepo.countByProductId(productid))
                    .build());
        }
        return qnaOutputDtos;
    }


    //질문 수정하기
    @Override
    public Qna editQnaById(QnaOutputDto qnaOutputDto) throws Exception {
        Qna qna = qnaRepo.findById(qnaOutputDto.getQnaId())
                .orElseThrow(QnaNotFoundException::new);
        qnaRepo.save(
                qna.builder()
                        .qnaId(qnaOutputDto.getQnaId())
                        .qnaTitle(qnaOutputDto.getQnaTitle())
                        .qnaContent(qnaOutputDto.getQnaContent())
                        .isSecret(qnaOutputDto.getIsSecret())
                        .qnaType(qnaOutputDto.getQnaType())
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