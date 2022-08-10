package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addProduct(RequestProductDto requestProductDto);

    List<Product> getAll();

    Product getProductById(Long id);

    Product editProductById(UpdateProductDto updateProductDto) throws Exception;

    void deleteProductById(Long id) throws Exception;

//    Cart addProductToCart(CartInputDto cartInputDto);
}
