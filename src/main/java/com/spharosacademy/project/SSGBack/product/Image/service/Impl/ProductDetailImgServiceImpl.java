package com.spharosacademy.project.SSGBack.product.Image.service.Impl;

import com.spharosacademy.project.SSGBack.product.Image.dto.CreateDetailImgDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ImageList;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ImageListRepository;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailImgServiceImpl implements ProductDetailImgService {

    private final ProductDetailImgRepository productDetailImgRepository;
    private final ImageListRepository imageListRepository;

    @Override
    public void createDetailImg(CreateDetailImgDto createDetailImgDto) {
        productDetailImgRepository.save(
                ProductDetailImage.builder()
                        .productDetailImgUrl(createDetailImgDto.getDetailImgUrl())
                        .productId(createDetailImgDto.getProductId())
                        .build()
        );

    }
}
