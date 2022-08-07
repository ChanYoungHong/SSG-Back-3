package com.spharosacademy.project.SSGBack.product.controller;

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

    @PostMapping("/add")
    public Product addProduct(
            @RequestBody RequestProductDto requestProductDto) {
        return productService.addProduct(requestProductDto);
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseProductDto> getProductById(@PathVariable Long id) {
        ResponseProductDto responseProductDto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseProductDto);
    }

    @PutMapping("/edit")
    public ResponseEntity<UpdateProductDto> editProduct
            (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        UpdateProductDto updateDto = productService.editProductById(updateProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateProductDto);
    }

    @PostMapping("/cartadd/{productId}")
    public Product addCartProduct(@PathVariable Long productId) {
        return cartService.addCartProduct(productId);
    }

}
