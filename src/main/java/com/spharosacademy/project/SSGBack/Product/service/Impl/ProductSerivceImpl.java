package com.spharosacademy.project.SSGBack.Product.service.Impl;

import com.spharosacademy.project.SSGBack.Product.domain.Product;
import com.spharosacademy.project.SSGBack.Product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.Product.repository.CategorySRepo;
import com.spharosacademy.project.SSGBack.Product.repository.ProductRepo;
import com.spharosacademy.project.SSGBack.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductSerivceImpl implements ProductService {


    private final ProductRepo productRepo;
    private final CategorySRepo categorySRepo;

    @Override
    public Product addProduct(ProductDto productDto) {

        return productRepo.save(Product.builder()
                .productId(productDto.getProductId())
                .productName(productDto.getProductName())
                .productColor(productDto.getProductColor())
                .productBrand(productDto.getProductBrand())
                .productCnt(productDto.getProductCnt())
                .productSellAmt(productDto.getProductSellAmt())
                .build());
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }
}
