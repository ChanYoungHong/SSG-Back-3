package com.spharosacademy.project.SSGBack.product.Image.service.Impl;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImgService;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCreateDetailImgDto;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailImgServiceImpl implements ProductDetailImgService {

    private final ProductDetailImgRepository productDetailImgRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductDetailImage addDetailImg(RequestCreateDetailImgDto requestCreateDetailImgDto) {
        productDetailImgRepository.save(
                ProductDetailImage.builder()
                        .imageUrl(requestCreateDetailImgDto.getDetailImgUrl())
                        .build()
        );
        return null;
    }

    @Override
    public List<ProductDetailImage> getImgByProductId(Long productId) {
        return productDetailImgRepository.findAllByproductId(productId);
    }

}
