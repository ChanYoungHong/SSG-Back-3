package com.spharosacademy.project.SSGBack.Qna.dto;

import lombok.AllArgsConstructor;
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

public class ResponseQnaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int qnaId;

    private String qnaTitle;

    private String qnaContent;

    private LocalDateTime qnaReg;

    private LocalDateTime qnaUpdate;

}
