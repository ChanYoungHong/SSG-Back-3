package com.spharosacademy.project.SSGBack.product.Image.service.Impl;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImageRepository;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailImageServiceImpl implements ProductDetailImageService {

    private final ProductDetailImageRepository productDetailImageRepository;

    @Override
    public List<ProductDetailImage> getAllByProductId(Long productId) {
        return productDetailImageRepository.findAllByProductId(productId);
    }
}
