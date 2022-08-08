package com.spharosacademy.project.SSGBack.product.Image.service;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCreateDetailImgDto;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import org.apache.coyote.Request;

import java.util.List;

public interface ProductDetailImgService {
    ProductDetailImage addDetailImg(RequestCreateDetailImgDto requestCreateDetailImgDto);

    List<ProductDetailImage> getImgByProductId(Long productId);
}
