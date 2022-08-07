package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.cart.domain.Cart;
import com.spharosacademy.project.SSGBack.cart.dto.input.CartInputDto;
import com.spharosacademy.project.SSGBack.cart.service.CartService;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    //관리자페이지 만들어진다면 옮길 예정?!
    @PostMapping("/add")
    public Product addProduct(
            @RequestBody RequestProductDto requestProductDto) {
        return productService.addProduct(requestProductDto);
    }

    //모든 상품들에 대한 정보 조회
    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    //특정 상품 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
    }
    
    //특정 상품 조회
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseProductDto> getProductById(@PathVariable Long id) {
        ResponseProductDto responseProductDto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseProductDto);
    }

    //특정 상품 수정 
    @PutMapping("/edit")
    public ResponseEntity<UpdateProductDto> editProduct
            (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        UpdateProductDto updateDto = productService.editProductById(updateProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateProductDto);
    }

    //상품 상세페이지에서 장바구니 담기
    @PostMapping("/get/{id}/addCart")
    public Cart addCart(@PathVariable Long id, @RequestBody CartInputDto cartInputDto) {
        return productService.addProductToCart(cartInputDto);
    }

}
