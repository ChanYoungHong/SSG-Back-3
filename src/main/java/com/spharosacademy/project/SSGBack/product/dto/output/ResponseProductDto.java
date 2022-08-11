package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.product.Image.entity.ImageList;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
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
    private String titleImgUrl;
    private String titleImgTxt;
    private int sellAmount;
    private String explanation;
    List<ImageList> imageLists;
    List<CategoryProductList> categoryProductLists;
    List<OptionList> optionLists;
}
