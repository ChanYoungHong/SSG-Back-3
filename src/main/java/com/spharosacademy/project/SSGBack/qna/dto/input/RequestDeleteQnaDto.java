package com.spharosacademy.project.SSGBack.qna.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeleteQnaDto {


    private Long qnaId;
}
