package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.category.dto.input.CreateCategoryListDto;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public Product addProduct(
            @RequestBody RequestProductDto requestProductDto,
            CreateCategoryListDto createCategoryListDto) {
        return productService.addProduct(requestProductDto, createCategoryListDto);
    }

    //모든 상품들에 대한 정보 조회
    @GetMapping("/getall")
    public List<ResponseProductDto> getAll() {
        return productService.getAll();
    }

    //특정 상품 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
    }

    //특정 상품 조회
    @GetMapping("/get/{id}")
    public ResponseProductDto getProductById(@PathVariable Long id) {
        return productService.getByProductId(id);
    }

    //특정 상품 수정 
    @PutMapping("/update")
    public Product editProduct
    (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        return productService.editProductById(updateProductDto);
    }

    //추천 상품들에 해당하는 상품 조회
    @GetMapping("/recommend/{id}")
    public ResponseRecommendProductDto getRecommendProductById(@PathVariable Long id) {
        return productService.getRecommendProductById(id);
    }

}
