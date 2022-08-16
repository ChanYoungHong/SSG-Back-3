package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.OutputSearchProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public Product addProduct(
            @RequestBody RequestProductDto requestProductDto) {
        return productService.addProduct(requestProductDto);
    }

    //모든 상품들에 대한 정보 조회
    @GetMapping("/getall")
    public ResponseEntity<List<ResponseProductDto>> getAll(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAll());
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

    @GetMapping("/search/{searchword}")
    public List<OutputSearchProductDto> SearchProductByWord(@PathVariable String searchword
            , @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.searchProductByWord(searchword, pageable);
    }

}
