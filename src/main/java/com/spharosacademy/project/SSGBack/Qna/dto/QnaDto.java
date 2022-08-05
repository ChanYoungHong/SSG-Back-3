package com.spharosacademy.project.SSGBack.Qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class QnaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int qnaId;

    private String qnaTitle;

    private String qnaContent;

    private Date qnaReg;

    private Date qnaUpdate;


}
