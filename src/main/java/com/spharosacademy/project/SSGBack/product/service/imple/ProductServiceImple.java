package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.ProductDto;
import com.spharosacademy.project.SSGBack.product.repository.CategorySRepository;
import com.spharosacademy.project.SSGBack.product.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImple implements ProductService {

    private final CategorySSRepository categorySSRepository;
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto productDto) {

        return productRepository.save(
                Product.builder()
                        .productName(productDto.getProductName())
                        .price(productDto.getPrice())
                        .productBrand(productDto.getProductBrand())
                        .productColor(productDto.getProductColor())
                        .productCnt(productDto.getProductCnt())
                        .categorySS(categorySSRepository.findById(productDto.getCategorySSId()).get())
                        .build()
        );
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product editProductById(Long id, ProductDto productDto) {
        return productRepository.save(
                Product.builder()
                        .productName(productDto.getProductName())
                        .productBrand(productDto.getProductBrand())
                        .productId(productDto.getProductId())
                        .productColor(productDto.getProductColor())
                        .productCnt(productDto.getProductCnt())
                        .price(productDto.getPrice())
                        .build()
        );
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
