package com.spharosacademy.project.SSGBack.Qna.dto;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD:src/main/java/com/spharosacademy/project/SSGBack/Qna/dto/ResponseQnaDto.java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
=======
import javax.persistence.*;
>>>>>>> origin/feature/product:src/main/java/com/spharosacademy/project/SSGBack/product/Image/entity/ProductTitleImage.java

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseQnaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD:src/main/java/com/spharosacademy/project/SSGBack/Qna/dto/ResponseQnaDto.java

    private int qnaId;

    private String qnaTitle;

    private String qnaContent;

    private LocalDateTime qnaReg;

    private LocalDateTime qnaUpdate;
=======
    private Long Id;
    private String productTitleImgUrl;
    private String productTitleImgTxt;

    @ManyToOne
    private Product product;
>>>>>>> origin/feature/product:src/main/java/com/spharosacademy/project/SSGBack/product/Image/entity/ProductTitleImage.java

}
