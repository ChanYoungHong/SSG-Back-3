package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.product.Image.service.ProductDetailImgService;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
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
    private final CartService cartService;
    private final ProductDetailImgService productDetailImgService;

    @PostMapping("/add")
    public Product addProduct(
            @RequestBody RequestProductDto requestProductDto) {
        return productService.addProduct(requestProductDto);
    }

    //모든 상품들에 대한 정보 조회
    @GetMapping("/all")
    public List<ResponseProductDto> getAll() {
        return productService.getAll();
    }

    //특정 상품 삭제
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
    }

    //특정 상품 조회
    @GetMapping("/{id}")
    public List<ResponseProductDto> getProductById(@PathVariable Long id) {
        productDetailImgService.getImgByProductId(id);
        return productService.getProductById(id);
    }

    //특정 상품 수정 
    @PutMapping("/update")
    public Product editProduct
    (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        return productService.editProductById(updateProductDto);

    }

}
