package com.spharosacademy.project.SSGBack.Qna.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class RequestQnaDto {



    private int qnaId;
    private String qnaTitle;
    private String qnaContent;
    private Integer isSecret;
    private Integer qnaType;


    private LocalDateTime createDate, updateDate;

    // ** Product id, user id 도 참고 해서 가져오기 그치만 recheck
    private Long productId;
    private Long userId;


}
