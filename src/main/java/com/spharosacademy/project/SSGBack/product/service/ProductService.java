package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.OutputSearchProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface ProductService {

    Product addProduct(RequestProductDto requestProductDto);

    List<ResponseProductDto> getAll();

    ResponseProductDto getByProductId(Long id, Long userid);

    Product editProductById(UpdateProductDto updateProductDto) throws Exception;

    void deleteProductById(Long id) throws Exception;

    ResponseRecommendProductDto getRecommendProductById(Long id, Long userid);

    Page<OutputSearchProductDto> searchProductByWord(String query, Long userid, Pageable pageable);

}
