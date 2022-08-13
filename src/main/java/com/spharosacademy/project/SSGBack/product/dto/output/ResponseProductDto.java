package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.ImageDetailDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.ImageTitleDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ResponseColorDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ResponseSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;
import com.spharosacademy.project.SSGBack.product.option.entity.SizeOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDto {

    private Long id;
    private String productName;
    private int price;
    private String priceText;
    private String productBrand;
    private int productCnt;
    private String mallTxt;
    private String thumbnailImgUrl;
    private int sellAmount;
    private String explanation;
    List<CategoryProductList> categoryProductLists;
    List<ImageDetailDto> imageDetailDtos;
    List<ImageTitleDto> imageTitleDtos;
    List<ResponseColorDto> responseColorDtos;
    List<ResponseSizeDto> responseSizeDtos;
}
