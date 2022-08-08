package com.spharosacademy.project.SSGBack.product.Image.controller;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImgService;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCreateDetailImgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ProductDetailImgController {

    private final ProductDetailImgService productDetailImgService;

    @PostMapping("/insert")
    public ProductDetailImage addDetailImg(
            @RequestBody RequestCreateDetailImgDto requestCreateDetailImgDto){
        return productDetailImgService.addDetailImg(requestCreateDetailImgDto);
    }

}
