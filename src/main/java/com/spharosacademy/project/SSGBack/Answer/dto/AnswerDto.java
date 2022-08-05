package com.spharosacademy.project.SSGBack.Answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AnswerDto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int answerId;

    private String answerTitle;

    private String answerContent;

    private Date answerReg;
}
