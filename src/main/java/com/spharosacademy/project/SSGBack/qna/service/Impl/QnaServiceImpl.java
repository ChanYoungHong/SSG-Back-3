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
    public Qna addQna(QnaInputDto qnaInputDto) {
//        Product product = productRepository.findbyId(requestDto.get
//                .findById(createRequest.getOrderProductNo())
//                .orElseThrow(OrderProductNotFoundException::new);
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

    // 질문 조회하기

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
                    .titleImgUrl(qna.getProduct().getThumbnailUrl())
                    .productName(qna.getProduct().getName())
                    .productBrand(qna.getProduct().getBrand())
                    .productid(qna.getProduct().getId())
                    .qnaType(qna.getQnaType())
                    .qnaTitle(qna.getQnaTitle())
                    .qnaContent(qna.getQnaContent())
                    .count(qnaRepo.countByProductId(productid))
                    .build());
        }
        return qnaOutputDtos;
    }

    //리스트 형식 제품아이디로 찾아서 가져와야함
    //상품을 조회할 때, 같이 갯수가 나간다.
    //


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
