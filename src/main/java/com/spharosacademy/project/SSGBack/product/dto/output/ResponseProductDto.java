package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.product.Image.dto.output.OutputDetailImgDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.OutputTitleImgDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.OptionOutputDto;
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

    private Long pid;
    private String name;
    private String mall;
    private String brand;
    private String thumbnailImgUrl;
    private int sellAmount;
    private float oprice;
    private float nprice;
    private float drate;
    ReviewTotalDto reviewTotalDto;
    private Long qnaCount;
    private Long wishId;
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
