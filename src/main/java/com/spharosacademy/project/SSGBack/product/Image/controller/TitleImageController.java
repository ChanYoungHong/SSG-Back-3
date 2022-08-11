package com.spharosacademy.project.SSGBack.product.Image.controller;


import com.spharosacademy.project.SSGBack.product.Image.dto.CreateTitleImgDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductTitleImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/titleImage")
@RequiredArgsConstructor
public class TitleImageController {

    private final ProductTitleImgService productTitleImgService;

    @PostMapping("/insert")
    public ProductTitleImage addTitleImage(@RequestBody CreateTitleImgDto createTitleImgDto){
        return productTitleImgService.createTitleImg(createTitleImgDto);
    }
}
