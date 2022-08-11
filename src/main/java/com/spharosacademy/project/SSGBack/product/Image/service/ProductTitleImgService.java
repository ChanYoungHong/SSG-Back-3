package com.spharosacademy.project.SSGBack.product.Image.service;

import com.spharosacademy.project.SSGBack.product.Image.dto.CreateTitleImgDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;

public interface ProductTitleImgService {

    ProductTitleImage createTitleImg(CreateTitleImgDto createTitleImgDto);
}
