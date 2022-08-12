package com.spharosacademy.project.SSGBack.Qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ResponseQnaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int qnaId;

    private String qnaTitle;

    private String qnaContent;

    private Boolean isSecret;

    private String qnaType;


    private LocalDateTime createDate, updateDate;

    // ** Product id, user id 도 참고 해서 가져오기 그치만 recheck
    private Long productId;
    private Long userId;


}