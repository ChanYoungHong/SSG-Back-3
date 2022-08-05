package com.spharosacademy.project.SSGBack.Qna.entity;

import com.spharosacademy.project.SSGBack.Product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qna")

public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "qna_id")
    private int qnaId;

    @Column(name = "qna_title")
    private String qnaTitle;

    @Column(name = "qna_content")
    private String qnaContent;

    @Column(name="qna_reg")
    private Date qnaReg;

    @Column(name = "qna_update")
    private Date qnaUpdate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
