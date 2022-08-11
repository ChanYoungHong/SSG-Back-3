package com.spharosacademy.project.SSGBack.product.Image.service.Impl;


import com.spharosacademy.project.SSGBack.product.Image.dto.CreateTitleImgDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductTitleImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.spharosacademy.project.SSGBack.product.Image.service.ProductTitleImgService;

@Service
@RequiredArgsConstructor
public class ProductTitleImgServiceImpl implements ProductTitleImgService {

    private final ProductTitleImgRepository productTitleImgRepository;

    @Override
    public ProductTitleImage createTitleImg(CreateTitleImgDto createTitleImgDto) {
        return productTitleImgRepository.save(
                ProductTitleImage.builder()
                        .productTitleImgUrl(createTitleImgDto.getTitleImgUrl())
                        .productId(createTitleImgDto.getProductId())
                        .build()
        );
    }
}
