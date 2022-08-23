package com.spharosacademy.project.SSGBack.recentWatch.service.impl;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.recentWatch.dto.response.ResponseRecentProductDto;
import com.spharosacademy.project.SSGBack.recentWatch.entity.RecentWatchProduct;
import com.spharosacademy.project.SSGBack.recentWatch.repository.RecentWatchProductRepository;
import com.spharosacademy.project.SSGBack.recentWatch.service.RecentWatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentWatchServiceImplement implements RecentWatchService {

    private final RecentWatchProductRepository recentWatchProductRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ResponseRecentProductDto> getAllRecentProduct(Long userId) {
        List<RecentWatchProduct> watchProductList = recentWatchProductRepository.findAllByUserId(userId);
        List<ResponseRecentProductDto> responseRecentProductDtos = new ArrayList<>();

        watchProductList.forEach(recentWatchProduct -> {
            Product product = productRepository.findById(recentWatchProduct.getProduct().getId()).get();
            responseRecentProductDtos.add(ResponseRecentProductDto.builder()
                    .brand(product.getBrand())
                    .mall(product.getMallText())
                    .price(product.getNewPrice())
                    .productId(product.getId())
                    .productName(product.getName())
                    .productThumbnailIImgUrl(product.getThumbnailUrl())
                    .build());
        });

        return responseRecentProductDtos;
    }
}
