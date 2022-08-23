package com.spharosacademy.project.SSGBack.qna.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestQnaDto {

    private Long userId;
    private Long productId;
    private String qnaTitle;
    private String qnaContent;
    private int isSecret;
    private int QnaType;
    private int qnaStatus;
}
