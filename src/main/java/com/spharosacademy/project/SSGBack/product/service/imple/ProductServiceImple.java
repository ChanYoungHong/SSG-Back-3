package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.category.repository.*;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.ImageDetailDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.ImageTitleDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductTitleImgRepository;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.*;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.option.dto.input.OptionInputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.OptionOutputDto;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

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
    private final OptionRepository optionRepository;


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
                        .cnt(requestProductDto.getCnt())
                        .sellAmt(requestProductDto.getSellAmount())
                        .explanation(requestProductDto.getExplanation())
                        .thumbnailUrl(requestProductDto.getThumbnailUrl())
                        .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
                        .build()
        );

        categoryProductListRepository.save(CategoryProductList.builder()
                .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
                .categoryS(categorySRepository.findById(requestProductDto.getCategorySId()).get())
                .categoryM(categoryMRepository.findById(requestProductDto.getCategoryMId()).get())
                .categoryL(categoryLRepository.findById(requestProductDto.getCategoryLId()).get())
                .Lname(categoryLRepository.findById(requestProductDto.getCategoryLId()).get().getName())
                .Mname(categoryMRepository.findById(requestProductDto.getCategoryMId()).get().getName())
                .Sname(categorySRepository.findById(requestProductDto.getCategorySId()).get().getName())
                .SSname(categorySSRepository.findById(requestProductDto.getCategorySSId()).get().getName())
                .product(product)
                .build());

        List<OptionInputDto> optionInputDtos = new ArrayList<>();
        for (OptionInputDto optionInputDto : requestProductDto.getOptionInputDtoList()) {
            optionInputDtos.add(OptionInputDto.builder()
                    .color(optionInputDto.getColor())
                    .size(optionInputDto.getSize())
                    .stock(optionInputDto.getStock())
                    .build());
        }

        optionInputDtos.forEach(optionInputDto -> {
            optionRepository.save(
                    OptionList.builder()
                            .color(optionInputDto.getColor())
                            .size(optionInputDto.getSize())
                            .stock(optionInputDto.getStock())
                            .product(product)
                            .build()
            );
        });

        requestProductDto.getCreateDetailImgDtoList().forEach(createDetailImgDto -> {
            productDetailImgRepository.save(
                    ProductDetailImage.builder()
                            .productDetailImgUrl(createDetailImgDto.getDetailImgUrl())
                            .productDetailImgTxt(createDetailImgDto.getDetailImgTxt())
                            .product(product)
                            .build()
            );
        });

        requestProductDto.getCreateTitleImgDtoList().forEach(createTitleImgDto -> {
            productTitleImgRepository.save(
                    ProductTitleImage.builder()
                            .productTitleImgUrl(createTitleImgDto.getTitleImgUrl())
                            .productTitleImgTxt(createTitleImgDto.getTitleImgTxt())
                            .product(product)
                            .build());
        });

        return product;
    }

    @Override
    public List<ResponseProductDto> getAll() {
        List<Product> ListProduct = productRepository.findAll();
        List<ResponseProductDto> responseProductDtoList = new ArrayList<>();

        ListProduct.forEach(product -> {
            List<ProductDetailImage> detailImageList = productDetailImgRepository.findAllByProduct(product);
            List<ImageDetailDto> detailDtoList = new ArrayList<>();

            for (ProductDetailImage detailImage : detailImageList) {
                detailDtoList.add(ImageDetailDto.builder()
                        .productDetailImgTxt(detailImage.getProductDetailImgTxt())
                        .productDetailImgUrl(detailImage.getProductDetailImgUrl())
                        .build());
            }

            List<ImageTitleDto> titleDtoList = new ArrayList<>();
            List<ProductTitleImage> titleImageList = productTitleImgRepository.findAllByProduct(product);

            for (ProductTitleImage productTitleImage : titleImageList) {
                titleDtoList.add(ImageTitleDto.builder()
                        .productTitleImgTxt(productTitleImage.getProductTitleImgTxt())
                        .productTitleImgUrl(productTitleImage.getProductTitleImgUrl())
                        .build());
            }

            List<OptionOutputDto> optionOutputDtoList = new ArrayList<>();
            List<OptionList> optionList = optionRepository.findAllByProduct(product);

            for (OptionList option : optionList) {
                optionOutputDtoList.add(OptionOutputDto.builder()
                        .color(option.getColor())
                        .size(option.getSize())
                        .stock(option.getStock())
                        .build());
            }


            List<CategoryProductList> lists = categoryProductListRepository.findAllByProduct(product);
            List<PofCategoryL> categoryLlist = new ArrayList<>();

            for (CategoryProductList categoryProductList : lists) {
                categoryLlist.add(PofCategoryL.builder()
                        .id(categoryProductList.getCategoryL().getId())
                        .name(categoryProductList.getLname())
                        .build());
            }


            List<PofCategoryM> categoryMList = new ArrayList<>();

            for (CategoryProductList categoryProductList : lists) {
                categoryMList.add(PofCategoryM.builder()
                        .id(categoryProductList.getCategoryM().getId())
                        .name(categoryProductList.getMname())
                        .build());
            }

            List<PofCategoryS> categorySList = new ArrayList<>();
            for (CategoryProductList categoryProductList : lists) {
                categorySList.add(PofCategoryS.builder()
                        .id(categoryProductList.getCategoryS().getId())
                        .name(categoryProductList.getSname())
                        .build());
            }

            List<PofCategorySS> categorySSList = new ArrayList<>();
            for (CategoryProductList categoryProductList : lists) {
                categorySSList.add(PofCategorySS.builder()
                        .id(categoryProductList.getCategorySS().getId())
                        .name(categoryProductList.getSSname())
                        .build());
            }

            responseProductDtoList.add(ResponseProductDto.builder()
                    .id(product.getId())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .priceText(product.getPriceText())
                    .mallTxt(product.getMallText())
                    .productBrand(product.getBrand())
                    .productCnt(product.getCnt())
                    .sellAmount(product.getSellAmt())
                    .explanation(product.getExplanation())
                    .thumbnailImgUrl(product.getThumbnailUrl())
                    .pofCategoryLList(categoryLlist)
                    .pofCategoryMList(categoryMList)
                    .pofCategorySList(categorySList)
                    .pofCategorySSList(categorySSList)
                    .imageDetailDtos(detailDtoList)
                    .imageTitleDtos(titleDtoList)
                    .optionOutputDtos(optionOutputDtoList)
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
                .mallText(recproduct.getMallText())
                .brand(recproduct.getBrand())
                .priceText(recproduct.getPriceText())
                .price(recproduct.getPrice())
                .titleImgUrl(recproduct.getThumbnailUrl())
                .build();
    }

    @Override
    public ResponseProductDto getByProductId(Long id) {
        Product product = productRepository.findById(id).get();

        List<ProductDetailImage> detailImageList = productDetailImgRepository.findAllByProduct(product);
        List<ImageDetailDto> detailDtoList = new ArrayList<>();

        for (ProductDetailImage detailImage : detailImageList) {
            detailDtoList.add(ImageDetailDto.builder()
                    .productDetailImgTxt(detailImage.getProductDetailImgTxt())
                    .productDetailImgUrl(detailImage.getProductDetailImgUrl())
                    .build());
        }

        List<ProductTitleImage> titleImageList = productTitleImgRepository.findAllByProduct(product);
        List<ImageTitleDto> titleDtoList = new ArrayList<>();

        for (ProductTitleImage productTitleImage : titleImageList) {
            titleDtoList.add(ImageTitleDto.builder()
                    .productTitleImgTxt(productTitleImage.getProductTitleImgTxt())
                    .productTitleImgUrl(productTitleImage.getProductTitleImgUrl())
                    .build());
        }

        List<OptionOutputDto> optionOutputDtoList = new ArrayList<>();
        List<OptionList> optionList = optionRepository.findAllByProduct(product);

        for (OptionList option : optionList) {
            optionOutputDtoList.add(OptionOutputDto.builder()
                    .color(option.getColor())
                    .size(option.getSize())
                    .stock(option.getStock())
                    .build());
        }

        List<CategoryProductList> lists = categoryProductListRepository.findAllByProduct(product);
        List<PofCategoryL> categoryLlist = new ArrayList<>();

        for (CategoryProductList categoryProductList : lists) {
            categoryLlist.add(PofCategoryL.builder()
                    .id(categoryProductList.getCategoryL().getId())
                    .name(categoryProductList.getLname())
                    .build());
        }

        List<PofCategoryM> categoryMList = new ArrayList<>();

        for (CategoryProductList categoryProductList : lists) {
            categoryMList.add(PofCategoryM.builder()
                    .id(categoryProductList.getCategoryM().getId())
                    .name(categoryProductList.getMname())
                    .build());
        }

        List<PofCategoryS> categorySList = new ArrayList<>();
        for (CategoryProductList categoryProductList : lists) {
            categorySList.add(PofCategoryS.builder()
                    .id(categoryProductList.getCategoryS().getId())
                    .name(categoryProductList.getSname())
                    .build());
        }

        List<PofCategorySS> categorySSList = new ArrayList<>();
        for (CategoryProductList categoryProductList : lists) {
            categorySSList.add(PofCategorySS.builder()
                    .id(categoryProductList.getCategorySS().getId())
                    .name(categoryProductList.getSSname())
                    .build());
        }

        return ResponseProductDto.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .productBrand(product.getBrand())
                .productCnt(product.getCnt())
                .priceText(product.getPriceText())
                .mallTxt(product.getMallText())
                .thumbnailImgUrl(product.getThumbnailUrl())
                .sellAmount(product.getSellAmt())
                .explanation(product.getExplanation())
                .pofCategoryLList(categoryLlist)
                .pofCategoryMList(categoryMList)
                .pofCategorySList(categorySList)
                .pofCategorySSList(categorySSList)
                .imageDetailDtos(detailDtoList)
                .imageTitleDtos(titleDtoList)
                .optionOutputDtos(optionOutputDtoList)
                .build();
    }

    @Override
    public Product editProductById(UpdateProductDto updateProductDto) throws Exception {
        Product product = productRepository.findById(updateProductDto.getProductId()).get();
        productRepository.save(
                Product.builder()
                        .id(updateProductDto.getProductId())
                        .name(updateProductDto.getProductName())
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


