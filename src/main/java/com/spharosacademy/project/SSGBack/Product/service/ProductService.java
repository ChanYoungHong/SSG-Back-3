package com.spharosacademy.project.SSGBack.Product.service;

import com.spharosacademy.project.SSGBack.Product.domain.Product;
import com.spharosacademy.project.SSGBack.Product.dto.ProductDto;

import java.util.List;

public interface ProductService {


    Product addProduct(ProductDto productDto);
    List<Product> getAll();



}
