package com.spharosacademy.project.SSGBack.Qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class RequestQnaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int qnaId;

    private String qnaTitle;

    private String qnaContent;

    private LocalDateTime qnaReg;

    private LocalDateTime qnaUpdate;


}
