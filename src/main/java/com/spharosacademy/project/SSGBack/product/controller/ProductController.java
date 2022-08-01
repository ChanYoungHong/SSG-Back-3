package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.domain.Product;
import com.spharosacademy.project.SSGBack.product.dto.ProductDto;
import com.spharosacademy.project.SSGBack.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @PostMapping("/product/add")
    public Product addProudct(@RequestBody ProductDto productDto){
        return iProductService.addProduct(productDto);
    }

    @GetMapping("/product/getAll")
    public List<Product> getAll(){
        return iProductService.getAll();
    }

}
