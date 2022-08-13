package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.category.dto.input.CreateCategoryListDto;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;

import java.util.List;

public interface ProductService {

    Product addProduct(RequestProductDto requestProductDto, CreateCategoryListDto createCategoryListDto);


    List<ResponseProductDto> getAll();

    ResponseProductDto getByProductId(Long id);

    Product editProductById(UpdateProductDto updateProductDto) throws Exception;

    void deleteProductById(Long id) throws Exception;

    ResponseRecommendProductDto getRecommendProductById(Long id);
}
