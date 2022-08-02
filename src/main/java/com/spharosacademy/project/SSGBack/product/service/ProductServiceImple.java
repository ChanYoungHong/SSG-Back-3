package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.CategoryL;
import com.spharosacademy.project.SSGBack.product.domain.CategoryS;
import com.spharosacademy.project.SSGBack.product.domain.Product;
import com.spharosacademy.project.SSGBack.product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.product.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImple implements ProductService {

    private final CategorySRepository categorySRepository;
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto productDto) {

        return productRepository.save(Product.builder()
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .productBrand(productDto.getProductBrand())
                .productColor(productDto.getProductColor())
                .productCnt(productDto.getProductCnt())
                .categoryS(categorySRepository.findById(productDto.getCategorySId()).get())
                .build());
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
