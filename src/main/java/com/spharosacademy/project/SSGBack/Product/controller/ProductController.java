package com.spharosacademy.project.SSGBack.Product.controller;

import com.spharosacademy.project.SSGBack.Product.domain.Product;
import com.spharosacademy.project.SSGBack.Product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {return productService.getAll();}

}
