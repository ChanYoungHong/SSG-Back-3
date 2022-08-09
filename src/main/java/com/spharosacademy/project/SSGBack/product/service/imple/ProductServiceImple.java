package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
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
        Product product = productRepository.save(
                Product.builder()
                        .name(requestProductDto.getName())
                        .price(requestProductDto.getPrice())
                        .brand(requestProductDto.getBrand())
                        .color(requestProductDto.getColor())
                        .cnt(requestProductDto.getCnt())
                        .titleImgUrl(requestProductDto.getTitleImgUrl())
                        .titleImgTxt(requestProductDto.getTitleImgTxt())
                        .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
                        .build()

        );

        requestProductDto.getProductDetailImageList().forEach(productDetailImage -> {
            productDetailImgRepository.save(ProductDetailImage.builder()
                    .imgUrl(productDetailImage.getImgUrl())
                    .imgTxt(productDetailImage.getImgTxt())
                    .product(product)
                    .build()
            );
        });
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> ListProduct = productRepository.findAll();
        return ListProduct;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    @Override
    public Product editProductById(UpdateProductDto updateProductDto) throws Exception {
        Product product = productRepository.findById(updateProductDto.getProductId()).get();
            productRepository.save(
                    Product.builder()
                            .id(updateProductDto.getProductId())
                            .name(updateProductDto.getProductName())
                            .color(updateProductDto.getProductColor())
                            .price(updateProductDto.getPrice())
                            .cnt(updateProductDto.getProductCnt())
                            .brand(updateProductDto.getProductBrand())
                            .categorySS(categorySSRepository.findById(updateProductDto.getCategorySSId()).get())
                            .build()
            );
        return product;
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
