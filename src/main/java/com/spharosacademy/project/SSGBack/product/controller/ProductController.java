package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.OutputSearchProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;

import java.awt.print.Pageable;

import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final JwtTokenProvider jwtTokenProvider;

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

    //특정 상품 조회(회원)
    @GetMapping("/user/get/{id}")
    public ResponseProductDto getProductById(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userid = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return productService.getByProductId(id, userid);
    }

    @GetMapping("/nonmember/get/{id}")
    public ResponseProductDto getProduct(@PathVariable Long id){
        Long userid = -1L ;
        return productService.getByProductId(id, userid);
    }

    //특정 상품 수정 
    @PutMapping("/update")
    public String editProduct
    (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        productService.editProductById(updateProductDto);
        return "상품 수정이 완료되었습니다";
    }

    //추천 상품들에 해당하는 상품 조회 - 회원
    @GetMapping("/user/recommend/{id}")
    public ResponseRecommendProductDto getRecommendProductById(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userid = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return productService.getRecommendProductById(id, userid);
    }

    @GetMapping("/nonmember/recommend/{id}")
    public ResponseRecommendProductDto getRecommendProductByNonMember(@PathVariable Long id){
        Long userid = -1L;
        return productService.getRecommendProductById(id, userid);
    }

    //상품 검색 - 회원
    @GetMapping("/user/search")
    public List<OutputSearchProductDto> SearchProductByWord(@RequestParam String query) {
        String token = jwtTokenProvider.customResolveToken();
        Long userid = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return productService.searchProductByWord(query, userid);
    }

    @GetMapping("/nonmember/search")
    public List<OutputSearchProductDto> searchProductByKeyword(@RequestParam String query) {
        Long userid = -1L;
        return productService.searchProductByWord(query, userid);
    }

}
