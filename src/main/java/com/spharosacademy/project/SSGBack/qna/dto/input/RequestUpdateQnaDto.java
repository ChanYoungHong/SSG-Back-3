package com.spharosacademy.project.SSGBack.qna.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateQnaDto {

    private Long qnaId;
    private Long productId;
    private Long memberId;
    private String qnaTitle;
    private String qnaContent;
    private int isSecret;
    private int qnaType;
}
