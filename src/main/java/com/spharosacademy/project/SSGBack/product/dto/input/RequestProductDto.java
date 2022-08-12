package com.spharosacademy.project.SSGBack.product.dto.input;

import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
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
    private Long colorId;
    private Long sizeId;
    private Long titleImgId;
    private String explanation;
    private int sellAmount;
    private String mallTxt;
    private int categorySSId;
    private int categorySId;
    private int categoryMId;
    private int categoryLId;

    List<ProductDetailImage> productDetailImageList;
    List<ProductTitleImage> productTitleImageList;

}
