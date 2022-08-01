package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.domain.Product;
import com.spharosacademy.project.SSGBack.product.dto.ProductDto;

import java.util.List;

public interface IProductService {

    Product addProduct(ProductDto productDto);
    List<Product> getAll();
}
