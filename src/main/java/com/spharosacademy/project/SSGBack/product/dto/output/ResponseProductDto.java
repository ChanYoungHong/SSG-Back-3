package com.spharosacademy.project.SSGBack.product.dto.output;

import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
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
    private String titleImgUrl;
    private int sellAmount;
    private String explanation;
    List<ProductDetailImage> productDetailImageList;
    List<ProductTitleImage> productTitleImageList;
    List<CategoryProductList> categoryProductLists;
    List<ColorOption> colorOptionList;
    List<SizeOption> sizeOptionList;
}
