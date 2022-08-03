package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.dto.input.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.ProductDto;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDto productDto);

    List<Product> getAll();

    Product getProductById(Long id);

    Product editProductById(Long productId, ProductDto productDto);

    void deleteProductById(Long id);
}
