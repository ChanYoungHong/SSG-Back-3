package com.spharosacademy.project.SSGBack.Qna.entity;

import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qna")

public class Qna extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @Column(name = "qna_id")
    private int qnaId;

//    @Column(name = "qna_title")
    private String qnaTitle;

//    @Column(name = "qna_content")
    private String qnaContent;

    // Boolean추가 사항?

 //   @Column(name = "is_Secret")
    private Boolean isSecret;

    public void changeQnaTitle(String qnaTitle) {
        this.qnaTitle = qnaTitle;
    }

    public void changeQnaContent(String qnaContent) {
        this.qnaContent = qnaContent;
    }


//나중에추가

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private User user;

}



//Enum 정해져있는 롤타입이 있을때만, Enum을 쓰고 아니면 숫자로 표현
//추가 해야 될것
//+ 문의 답변 상태 boolean
//+ 비밀글 설정 boolean
//+ 문의 타입 varchar(10)
// 회원일련번호 member id. 연결
// 상품 일련 번호 product id. 연결
// Notnull 는 default 값 ?
// (fetch = FetchType.LAZY) 지연로딩은 그만큼 많은 양의 데이터가 있을 때,

// @para ??

