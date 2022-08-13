package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.category.dto.input.CreateCategoryListDto;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryLDto;
import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.category.repository.*;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.ImageDetailDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.ImageTitleDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductTitleImgRepository;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseRecommendProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ResponseColorDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ResponseSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;
import com.spharosacademy.project.SSGBack.product.option.entity.SizeOption;
import com.spharosacademy.project.SSGBack.product.option.repository.ColorOptionRepository;
import com.spharosacademy.project.SSGBack.product.option.repository.SizeOptionRepository;
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
    private final ColorOptionRepository colorOptionRepository;
    private final SizeOptionRepository sizeOptionRepository;


    @Override
    public Product addProduct(RequestProductDto requestProductDto
            , CreateCategoryListDto createCategoryListDto) {
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
                        .build()
        );

        categoryProductListRepository.save(CategoryProductList.builder()
                .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
                .categoryS(categorySRepository.findById(requestProductDto.getCategorySId()).get())
                .categoryM(categoryMRepository.findById(requestProductDto.getCategoryMId()).get())
                .categoryL(categoryLRepository.findById(requestProductDto.getCategoryLId()).get())
                .product(product)
                .build());

        requestProductDto.getCreateColorDtos().forEach(createColorDto -> {
            colorOptionRepository.save(
                    ColorOption.builder()
                            .colorType(createColorDto.getName())
                            .product(product)
                            .build()
            );
        });

        requestProductDto.getCreateSizeDtos().forEach(createSizeDto -> {
            sizeOptionRepository.save(
                    SizeOption.builder()
                            .sizeType(createSizeDto.getSize())
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

            List<ResponseColorDto> colorDtoList = new ArrayList<>();
            List<ColorOption> colorOptionList = colorOptionRepository.findAllByProduct(product);

            for (ColorOption colorOption : colorOptionList) {
                colorDtoList.add(ResponseColorDto.builder()
                        .color(colorOption.getColorType())
                        .build());
            }

            List<ResponseSizeDto> sizeDtoList = new ArrayList<>();
            List<SizeOption> sizeOptionList = sizeOptionRepository.findAllByProduct(product);

            for (SizeOption sizeOption : sizeOptionList) {
                sizeDtoList.add(ResponseSizeDto.builder()
                        .size(sizeOption.getSizeType())
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
                    .categoryProductLists(categoryProductListRepository.findAllByProductId(product))
                    .imageDetailDtos(detailDtoList)
                    .imageTitleDtos(titleDtoList)
                    .responseColorDtos(colorDtoList)
                    .responseSizeDtos(sizeDtoList)
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

        List<ImageTitleDto> titleDtoList = new ArrayList<>();
        List<ProductTitleImage> titleImageList = productTitleImgRepository.findAllByProduct(product);

        for (ProductTitleImage productTitleImage : titleImageList) {
            titleDtoList.add(ImageTitleDto.builder()
                    .productTitleImgTxt(productTitleImage.getProductTitleImgTxt())
                    .productTitleImgUrl(productTitleImage.getProductTitleImgUrl())
                    .build());
        }

        List<ResponseColorDto> colorDtoList = new ArrayList<>();
        List<ColorOption> colorOptionList = colorOptionRepository.findAllByProduct(product);

        for (ColorOption colorOption : colorOptionList) {
            colorDtoList.add(ResponseColorDto.builder()
                    .color(colorOption.getColorType())
                    .build());
        }

        List<ResponseSizeDto> sizeDtoList = new ArrayList<>();
        List<SizeOption> sizeOptionList = sizeOptionRepository.findAllByProduct(product);

        for (SizeOption sizeOption : sizeOptionList) {
            sizeDtoList.add(ResponseSizeDto.builder()
                    .size(sizeOption.getSizeType())
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
                .categoryProductLists(categoryProductListRepository.findAllByProductId(product))
                .imageDetailDtos(detailDtoList)
                .imageTitleDtos(titleDtoList)
                .responseSizeDtos(sizeDtoList)
                .responseColorDtos(colorDtoList)
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


