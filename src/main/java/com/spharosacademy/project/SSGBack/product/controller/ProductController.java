package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.OutputSearchProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import java.awt.print.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public String addProduct(
            @RequestBody RequestProductDto requestProductDto) {
        productService.addProduct(requestProductDto);
        return "상품 등록이 완료되었습니다";
    }

    //모든 상품들에 대한 정보 조회
    @GetMapping("/getall")
    public ResponseEntity<List<ResponseProductDto>> getAll(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAll());
    }

    //특정 상품 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
        return "상품 삭제가 완료되었습니다";
    }

    //특정 상품 조회
    @GetMapping("/get/{id}")
    public ResponseProductDto getProductById(@PathVariable Long id) {
        return productService.getByProductId(id);
    }

    //특정 상품 수정 
    @PutMapping("/update")
    public String editProduct
    (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        productService.editProductById(updateProductDto);
        return "상품 수정이 완료되었습니다";
    }

    //추천 상품들에 해당하는 상품 조회
    @GetMapping("/recommend/{id}")
    public ResponseRecommendProductDto getRecommendProductById(@PathVariable Long id) {
        return productService.getRecommendProductById(id);
    }

    @GetMapping("/search")
    public List<OutputSearchProductDto> SearchProductByWord(@RequestParam String query
            , @PageableDefault(size = 20, sort = "createDate", direction = Sort.Direction.ASC)
                                                                Pageable pageable) {
        return productService.searchProductByWord(query, pageable);
    }

}
