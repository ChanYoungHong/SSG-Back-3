package com.spharosacademy.project.SSGBack.product.Image.controller;

import com.spharosacademy.project.SSGBack.product.Image.dto.CreateDetailImgDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detailImage")
@RequiredArgsConstructor
public class DetailImageController {

    private final ProductDetailImgService productDetailImgService;

    @PostMapping("/insert")
    public void addDetailImage(@RequestBody CreateDetailImgDto createDetailImgDto) {
        productDetailImgService.createDetailImg(createDetailImgDto);
    }

}
