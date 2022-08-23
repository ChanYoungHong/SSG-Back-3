package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.product.Image.dto.output.OutputDetailImgDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.OutputTitleImgDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.OptionOutputDto;
import com.spharosacademy.project.SSGBack.qna.dto.output.ResponseProductQnaDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDto {

    private Long id;
    private String productName;
    private String mallTxt;
    private String productBrand;
    private int productCnt;
    private String thumbnailImgUrl;
    private int sellAmount;
    private float oldPrice;
    private float newPrice;
    private float discountRate;
    private String priceText;
    ReviewTotalDto reviewTotalDto;
    List<ResponseProductReviewDto> responseProductReviewDtos;
    List<ResponseProductQnaDto> responseProductQnaDtoList;
    private Long qnaCount;
    private String explanation;
    private LocalDateTime regDate;
    List<PofCategorySS> pofCategorySSList;
    List<PofCategoryS> pofCategorySList;
    List<PofCategoryM> pofCategoryMList;
    List<PofCategoryL> pofCategoryLList;
    List<OutputDetailImgDto> outputDetailImgDtos;
    List<OutputTitleImgDto> outputTitleImgDtos;
    List<OptionOutputDto> optionOutputDtos;
}
