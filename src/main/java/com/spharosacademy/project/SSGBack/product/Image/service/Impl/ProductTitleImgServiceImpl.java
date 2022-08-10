package com.spharosacademy.project.SSGBack.product.Image.service.Impl;


import com.spharosacademy.project.SSGBack.product.Image.repository.ProductTitleImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.spharosacademy.project.SSGBack.product.Image.service.ProductTtitleImgService;

@Service
@RequiredArgsConstructor
public class ProductTitleImgServiceImpl implements ProductTtitleImgService {

    private final ProductTitleImgRepository productTitleImgRepository;
}
