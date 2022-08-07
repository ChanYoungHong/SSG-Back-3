package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;

import java.util.List;

public interface ProductService {

    Product addProduct(RequestProductDto requestProductDto);

    List<Product> getAll();

    ResponseProductDto getProductById(Long id);

    UpdateProductDto editProductById(UpdateProductDto updateProductDto) throws Exception;

    void deleteProductById(Long id) throws Exception;

    Cart addProductToCart(CartInputDto cartInputDto);
}
