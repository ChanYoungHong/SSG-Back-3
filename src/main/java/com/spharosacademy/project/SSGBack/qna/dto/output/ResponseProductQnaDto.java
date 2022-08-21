package com.spharosacademy.project.SSGBack.qna.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductQnaDto {

    private Long productId;
    private String userLoginId;
    private Long qnaId;
    private String qnaTitle;
    private String qnaContent;
    private int isSecret;
    private int qnaType;
}
