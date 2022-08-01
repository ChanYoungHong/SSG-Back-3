package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.LastCategory;
import com.spharosacademy.project.SSGBack.product.domain.Product;
import com.spharosacademy.project.SSGBack.product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.product.repository.ILastCategoryRepository;
import com.spharosacademy.project.SSGBack.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImple implements IProductService{

    private final ILastCategoryRepository iLastCategoryRepository;
    private final IProductRepository iProductRepository;

    @Override
    public Product addProduct(ProductDto productDto) {
        LastCategory lastCategory = iLastCategoryRepository.findById(productDto.getLastCategoryId()).get();
        return iProductRepository.save(Product.builder()
                .name(productDto.getName())
                .lastCategory(lastCategory)
                .build());
    }

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }
}
