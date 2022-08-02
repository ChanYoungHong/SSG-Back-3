package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.LastCategory;
import com.spharosacademy.project.SSGBack.product.domain.Product;
import com.spharosacademy.project.SSGBack.product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.product.repository.ILastCategoryRepository;
import com.spharosacademy.project.SSGBack.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImple implements IProductService {

    private final ILastCategoryRepository iLastCategoryRepository;
    private final IProductRepository iProductRepository;

    @Override
    public Product addProduct(ProductDto productDto) {

        Optional<LastCategory> lastCategory = iLastCategoryRepository.findById(productDto.getLastCategoryId());
        if(lastCategory.isEmpty()) {
            log.info("데이터부터 넣어요");
            return null;
        }
        log.info("{}", lastCategory);
        return iProductRepository.save(Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .lastCategory(lastCategory.get())
                .build());
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }
}
