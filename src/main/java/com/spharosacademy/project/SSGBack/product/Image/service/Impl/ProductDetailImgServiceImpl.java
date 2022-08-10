package com.spharosacademy.project.SSGBack.product.Image.service.Impl;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImgService;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailImgServiceImpl implements ProductDetailImgService {

    private final ProductDetailImgRepository productDetailImgRepository;


    @Override
    public List<ProductDetailImage> getImgByProductId(Long productId) {
        return productDetailImgRepository.findAllByproductId(productId);
    }

}
