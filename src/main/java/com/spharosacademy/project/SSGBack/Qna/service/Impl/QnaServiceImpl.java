package com.spharosacademy.project.SSGBack.Qna.service.Impl;

import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import com.spharosacademy.project.SSGBack.Qna.dto.QnaDto;
import com.spharosacademy.project.SSGBack.Qna.repository.QnaRepo;
import com.spharosacademy.project.SSGBack.Qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class QnaServiceImpl implements QnaService {

    private final QnaRepo qnaRepo;

    // 질문하기
    @Override
    public Qna addQna(QnaDto qnaDto) {
        return qnaRepo.save(Qna.builder()
                .qnaId(qnaDto.getQnaId())
                .qnaTitle(qnaDto.getQnaTitle())
                .qnaContent(qnaDto.getQnaContent())
                .qnaReg(qnaDto.getQnaReg())
                .qnaUpdate(qnaDto.getQnaUpdate())
                .build());
    }

    // 조회하기

    @Override
    public List<Qna> getAll() {
        return qnaRepo.findAll();
    }


}
