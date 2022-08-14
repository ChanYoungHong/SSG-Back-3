package com.spharosacademy.project.SSGBack.product.dto.input;

import com.spharosacademy.project.SSGBack.product.Image.dto.input.CreateDetailImgDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.input.CreateTitleImgDto;
import com.spharosacademy.project.SSGBack.product.option.dto.input.OptionInputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductDto {

    private String name;
    private int price;
    private String priceText;
    private String brand;
    private int cnt;
    private String thumbnailUrl;
    private String explanation;
    private int sellAmount;
    private String mallTxt;
    private int categorySSId;
    private int categorySId;
    private int categoryMId;
    private int categoryLId;

    List<CreateDetailImgDto> createDetailImgDtoList;
    List<CreateTitleImgDto> createTitleImgDtoList;
    List<OptionInputDto> optionInputDtoList;

}
