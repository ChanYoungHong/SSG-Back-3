package com.spharosacademy.project.SSGBack.Answer.entity;


import com.spharosacademy.project.SSGBack.Qna.entity.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "answer")

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "answer_id")
    private int answerId;

    @Column(name = "answer_title")
    private String answerTitle;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "answer_reg")
    private Date answerReg;

    @ManyToOne
    @JoinColumn(name = "qna_id")
    private Qna qna;
}
