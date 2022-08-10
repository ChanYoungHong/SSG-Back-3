package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.category.entity.CategoryProductList;
import com.spharosacademy.project.SSGBack.category.repository.*;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductTitleImgRepository;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImple implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailImgRepository productDetailImgRepository;
    private final CategoryProductListRepository categoryProductListRepository;
    private final CategorySSRepository categorySSRepository;
    private final CategoryMRepository categoryMRepository;
    private final CategorySRepository categorySRepository;
    private final CategoryLRepository categoryLRepository;
    private final ProductTitleImgRepository productTitleImgRepository;

    @Override
    public Product addProduct(RequestProductDto requestProductDto) {
        Product product = productRepository.save(
                Product.builder()
                        .name(requestProductDto.getName())
                        .sellAmt(requestProductDto.getSellAmount())
                        .priceText(requestProductDto.getPriceText())
                        .mallText(requestProductDto.getMallTxt())
                        .price(requestProductDto.getPrice())
                        .brand(requestProductDto.getBrand())
                        .color(requestProductDto.getColor())
                        .cnt(requestProductDto.getCnt())
                        .sellAmt(requestProductDto.getSellAmount())
                        .titleImgUrl(requestProductDto.getTitleImgUrl())
                        .titleImgTxt(requestProductDto.getTitleImgTxt())
                        .explanation(requestProductDto.getExplanation())
                        .build()
        );

        categoryProductListRepository.save(CategoryProductList.builder()
                .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
                .categoryS(categorySRepository.findById(requestProductDto.getCategorySId()).get())
                .categoryM(categoryMRepository.findById(requestProductDto.getCategoryMId()).get())
                .categoryL(categoryLRepository.findById(requestProductDto.getCategoryLId()).get())
                .product(product)
                .build());


        requestProductDto.getProductDetailImageList().forEach(
                productDetailImage -> {
                    productDetailImgRepository.save(ProductDetailImage.builder()
                            .imgUrl(productDetailImage.getImgUrl())
                            .imgTxt(productDetailImage.getImgTxt())
                            .product(product)
                            .build()
                    );
                });


        productTitleImgRepository.save(ProductTitleImage.builder()
                .productTitleImgUrl(product.getTitleImgUrl())
                .productTitleImgTxt(product.getTitleImgTxt())
                .product(product)
                .build());
        return product;
    }

    @Override
    public List<ResponseProductDto> getAll() {
        List<Product> ListProduct = productRepository.findAll();
        List<ResponseProductDto> responseProductDtoList = new ArrayList<>();
        ListProduct.forEach(product -> {
            responseProductDtoList.add(ResponseProductDto.builder()
                    .id(product.getId())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .priceText(product.getPriceText())
                    .mallTxt(product.getMallText())
                    .productColor(product.getColor())
                    .productBrand(product.getBrand())
                    .productCnt(product.getCnt())
                    .titleImgUrl(product.getTitleImgUrl())
                    .titleImgTxt(product.getTitleImgTxt())
                    .sellAmount(product.getSellAmt())
                    .explanation(product.getExplanation())
                    .categoryProductLists(categoryProductListRepository.findAllByProductId(product.getId()))
                    .productDetailImageList(productDetailImgRepository.findAllByproductId(product.getId()))
                    .build());
        });
        return responseProductDtoList;
    }


    @Override
    public ResponseRecommendProductDto getRecommendProductById(Long id) {
        Product recproduct = productRepository.findById(id).get();

        return ResponseRecommendProductDto.builder()
                .id(recproduct.getId())
                .name(recproduct.getName())
                .titleImgUrl(recproduct.getTitleImgUrl())
                .mallText(recproduct.getMallText())
                .brand(recproduct.getBrand())
                .priceText(recproduct.getPriceText())
                .price(recproduct.getPrice())
                .build();
    }

    ;

    @Override
    public ResponseProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        CategoryProductList categoryProductList = categoryProductListRepository.findAllByProductId(id).get(0);
        log.info("{}", categoryProductList);

        return ResponseProductDto.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .productColor(product.getColor())
                .productBrand(product.getBrand())
                .productCnt(product.getCnt())
                .priceText(product.getPriceText())
                .mallTxt(product.getMallText())
                .titleImgUrl(product.getTitleImgUrl())
                .titleImgTxt(product.getTitleImgTxt())
                .sellAmount(product.getSellAmt())
                .explanation(product.getExplanation())
                .productDetailImageList(productDetailImgRepository.findAllByproductId(product.getId()))
                .categoryProductLists(categoryProductListRepository.findAllByProductId(product.getId()))
                .build();
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
