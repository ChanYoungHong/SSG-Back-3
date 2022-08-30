package com.spharosacademy.project.SSGBack.product.service;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.*;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;

import com.spharosacademy.project.SSGBack.product.option.dto.output.ColorOutputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.SizeOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Product addProduct(RequestProductDto requestProductDto, MultipartFile multipartFile,
                       List<MultipartFile> multipartFileList, List<MultipartFile> titleFileList) throws IOException;

    List<ResponseProductDto> getAll();

    ResponseProductDto getByProductId(Long id, Long userid);

    Product editProductById(UpdateProductDto updateProductDto) throws Exception;

    void deleteProductById(Long id) throws Exception;

    ResponseRecommendProductDto getRecommendProductById(Long id, Long userid);

    Page<OutputSearchProductDto> searchProductByWord(String query, Long userid, Pageable pageable);

    List<ColorOutputDto> getProductColor(Long id);

    List<SizeOutputDto>  getProductSize(Long productId, Long colorId);
}
