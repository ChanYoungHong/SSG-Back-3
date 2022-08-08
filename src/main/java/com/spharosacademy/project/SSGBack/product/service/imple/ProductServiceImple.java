package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImple implements ProductService {

    private final CategorySSRepository categorySSRepository;
    private final ProductRepository productRepository;
    private final ProductDetailImgRepository productDetailImgRepository;

    @Override
    public Product addProduct(RequestProductDto requestProductDto) {
        productRepository.save(
                Product.builder()
                        .productName(requestProductDto.getProductName())
                        .price(requestProductDto.getPrice())
                        .productBrand(requestProductDto.getProductBrand())
                        .productColor(requestProductDto.getProductColor())
                        .productCnt(requestProductDto.getProductCnt())
                        .detailImgUrl(requestProductDto.getDetailImgUrl())
                        .titleImgUrl(requestProductDto.getTitleImgUrl())
                        .detailImgUrl(requestProductDto.getDetailImgUrl())
                        .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).orElseThrow())
                        .build()
        );
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> ListProduct = productRepository.findAll();
        return ListProduct;
    }

    @Override
    public ResponseProductDto getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(value -> ResponseProductDto.builder()
                .productId(value.getProductId())
                .productName(value.getProductName())
                .productCnt(value.getProductCnt())
                .productBrand(value.getProductBrand())
                .productColor(value.getProductColor())
                .productDetailImageList(productDetailImgRepository.findAllByProductId(productId))
                .build());
        return null;
    }

    @Override
    public UpdateProductDto editProductById(UpdateProductDto updateProductDto) throws Exception {
        Optional<Product> product = productRepository.findById(updateProductDto.getProductId());
        if (product.isPresent()) {
            UpdateProductDto.builder()
                    .ProductId(updateProductDto.getProductId())
                    .productName(updateProductDto.getProductName())
                    .productColor(updateProductDto.getProductColor())
                    .price(updateProductDto.getPrice())
                    .productCnt(updateProductDto.getProductCnt())
                    .productBrand(updateProductDto.getProductBrand())
                    .CategorySSId(updateProductDto.getCategorySSId())
                    .build();
            return null;
        } else {
            throw new Exception();
        }
    }

    @Override
    public void deleteProductById(Long id) throws Exception {
        Optional<Product> delbyid = productRepository.findById(id);
        if (delbyid.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new Exception();
        }
    }
}
