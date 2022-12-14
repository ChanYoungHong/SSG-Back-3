package com.spharosacademy.project.SSGBack.qna.service.impl;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestDeleteQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.input.RequestUpdateQnaDto;
import com.spharosacademy.project.SSGBack.qna.dto.output.ResponseProductQnaDto;
import com.spharosacademy.project.SSGBack.qna.entity.QnA;
import com.spharosacademy.project.SSGBack.qna.repository.QnaRepository;
import com.spharosacademy.project.SSGBack.qna.service.QnaService;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaServiceImplement implements QnaService {

    private final QnaRepository qnaRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addQna(RequestQnaDto requestQnaDto,Long userId) {
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(requestQnaDto.getProductId()).get();
        qnaRepository.save(QnA.builder()
                .user(user)
                .product(product)
                .isSecret(requestQnaDto.getIsSecret())
                .qnaContent(requestQnaDto.getQnaContent())
                .qnaTitle(requestQnaDto.getQnaTitle())
                .qnaType(requestQnaDto.getQnaType())
                .qnaStatus(requestQnaDto.getQnaStatus())
                .build());
    }

    @Override
    public List<ResponseProductQnaDto> getQnaByProductId(Long productId) {
        List<QnA> qnAList = qnaRepository.findAllByProductId(productId);
        List<ResponseProductQnaDto> responseProductQnaDtoList = new ArrayList<>();
        qnAList.forEach(qnA -> {
            responseProductQnaDtoList.add(ResponseProductQnaDto.builder()
                    .productId(qnA.getProduct().getId())
                    .qnaId(qnA.getId())
                    .userLoginId(qnA.getUser().getUserId())
                    .isSecret(qnA.getIsSecret())
                    .qnaContent(qnA.getQnaContent())
                    .qnaTitle(qnA.getQnaTitle())
                    .qnaType(qnA.getQnaType())
                    .regDate(qnA.getCreateDate())
                    .updateDate(qnA.getUpdatedDate())
                    .qnaStatus(qnA.getQnaStatus())
                    .build());
        });
        return responseProductQnaDtoList;
    }

    @Override
    public QnA editQna(RequestUpdateQnaDto requestUpdateQnaDto, Long userId) {
        Product product = productRepository.findById(requestUpdateQnaDto.getProductId()).get();
        User user = userRepository.findById(userId).get();
        QnA qnA = qnaRepository.save(QnA.builder()
                .product(product)
                .user(user)
                .qnaType(requestUpdateQnaDto.getQnaType())
                .qnaTitle(requestUpdateQnaDto.getQnaTitle())
                .qnaContent(requestUpdateQnaDto.getQnaContent())
                .isSecret(requestUpdateQnaDto.getIsSecret())
                .id(requestUpdateQnaDto.getQnaId())
                .build());

        return qnA;
    }

    @Override
    public void deleteQna(RequestDeleteQnaDto requestDeleteQnaDto,Long userId) {
        qnaRepository.deleteById(requestDeleteQnaDto.getQnaId());
    }
}
