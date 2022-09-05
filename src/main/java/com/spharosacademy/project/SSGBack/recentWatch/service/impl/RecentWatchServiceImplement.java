package com.spharosacademy.project.SSGBack.recentWatch.service.impl;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentProductDto;
import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentQueryDto;
import com.spharosacademy.project.SSGBack.recentWatch.entity.RecentWatchProduct;
import com.spharosacademy.project.SSGBack.recentWatch.repository.RecentWatchProductRepository;
import com.spharosacademy.project.SSGBack.recentWatch.repository.RecentWatchQueryRepository;
import com.spharosacademy.project.SSGBack.recentWatch.service.RecentWatchService;
import com.spharosacademy.project.SSGBack.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentWatchServiceImplement implements RecentWatchService {

    private final RecentWatchProductRepository recentWatchProductRepository;
    private final ProductRepository productRepository;
    private final RecentWatchQueryRepository recentWatchQueryRepository;
    private final WishListRepository wishListRepository;

    @Override
    public List<ResponseRecentProductDto> getAllRecentProduct(Long userId) {
        List<RecentWatchProduct> watchProductList = recentWatchProductRepository.findAllByUserId(userId);
        List<ResponseRecentProductDto> responseRecentProductDtos = new ArrayList<>();
        if (userId != null) {
            watchProductList.forEach(recentWatchProduct -> {
                Product product = productRepository.findById(recentWatchProduct.getProduct().getId()).get();
                if (wishListRepository.findByUserIdAndProductId(userId, product.getId()) == null) {
                    Long wId = null;
                    responseRecentProductDtos.add(ResponseRecentProductDto.builder()
                            .productBrand(product.getBrand())
                            .mallTxt(product.getMallText())
                            .newPrice(product.getNewPrice())
                            .id(product.getId())
                            .productName(product.getName())
                            .thumbnailImgUrl(product.getThumbnailUrl())
                            .wishId(wId)
                            .build());
                } else {
                    responseRecentProductDtos.add(ResponseRecentProductDto.builder()
                            .productBrand(product.getBrand())
                            .mallTxt(product.getMallText())
                            .newPrice(product.getNewPrice())
                            .id(product.getId())
                            .productName(product.getName())
                            .thumbnailImgUrl(product.getThumbnailUrl())
                            .wishId(wishListRepository.findByUserIdAndProductId(userId, product.getId()))
                            .build());
                }
            });
        } else {
            Long wishId = null;
            watchProductList.forEach(recentWatchProduct -> {
                Product product = productRepository.findById(recentWatchProduct.getProduct().getId()).get();
                responseRecentProductDtos.add(ResponseRecentProductDto.builder()
                        .productBrand(product.getBrand())
                        .mallTxt(product.getMallText())
                        .newPrice(product.getNewPrice())
                        .id(product.getId())
                        .productName(product.getName())
                        .thumbnailImgUrl(product.getThumbnailUrl())
                        .wishId(wishId)
                        .build());
            });
        }
        return responseRecentProductDtos;
    }

    @Override
    public List<ResponseRecentQueryDto> getAllRecentQuery(Long userId) {
        List<ResponseRecentQueryDto> recentQueryDtoList = recentWatchQueryRepository.getByUserId(userId);
        return recentQueryDtoList;
    }

    @Override
    public void deleteProduct(Long id, Long userId) {
        recentWatchProductRepository.deleteById(id);
    }

    @Override
    public void deleteQuery(Long id, Long userId) {
        recentWatchQueryRepository.deleteById(id);
    }
}
