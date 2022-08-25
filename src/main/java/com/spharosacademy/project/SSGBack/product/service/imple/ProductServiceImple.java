package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.category.exception.CategoryNotFoundException;
import com.spharosacademy.project.SSGBack.category.repository.*;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.OutputDetailImgDto;
import com.spharosacademy.project.SSGBack.product.Image.dto.output.OutputTitleImgDto;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductDetailImage;
import com.spharosacademy.project.SSGBack.product.Image.entity.ProductTitleImage;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductDetailImgRepository;
import com.spharosacademy.project.SSGBack.product.Image.repository.ProductTitleImgRepository;
import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.*;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.exception.ProductNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.dto.input.OptionInputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.OptionOutputDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Colors;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import com.spharosacademy.project.SSGBack.product.option.entity.Size;
import com.spharosacademy.project.SSGBack.product.option.repository.ColorRepository;
import com.spharosacademy.project.SSGBack.product.option.repository.OptionRepository;
import com.spharosacademy.project.SSGBack.product.option.repository.SizeRepository;
import com.spharosacademy.project.SSGBack.product.repo.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import com.spharosacademy.project.SSGBack.qna.dto.output.ResponseProductQnaDto;
import com.spharosacademy.project.SSGBack.qna.entity.QnA;
import com.spharosacademy.project.SSGBack.qna.repository.QnaRepository;
import com.spharosacademy.project.SSGBack.review.dto.output.OutputReviewImgDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.image.repo.ReviewImageRepository;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
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
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ReviewRepository reviewRepository;
    private final QnaRepository qnaRepository;
    private final ReviewImageRepository reviewImageRepository;


    @Override
    public Product addProduct(RequestProductDto requestProductDto) {
        Product product = productRepository.save(
                Product.builder()
                        .name(requestProductDto.getName())
                        .sellAmt(requestProductDto.getSellAmount())
                        .priceText(requestProductDto.getPriceText())
                        .mallText(requestProductDto.getMallTxt())
                        .oldPrice(requestProductDto.getOldPrice())
                        .discountRate(requestProductDto.getDiscountRate())
                        .newPrice(requestProductDto.getOldPrice() * (1 - requestProductDto.getDiscountRate()))
                        .brand(requestProductDto.getBrand())
                        .sellAmt(requestProductDto.getSellAmount())
                        .explanation(requestProductDto.getExplanation())
                        .thumbnailUrl(requestProductDto.getThumbnailUrl())
                        .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId())
                                .orElseThrow(CategoryNotFoundException::new))
                        .build()
        );

        categoryProductListRepository.save(CategoryProductList.builder()
                .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId())
                        .orElseThrow(ProductNotFoundException::new))
                .categoryS(categorySRepository.findById(requestProductDto.getCategorySId())
                        .orElseThrow(CategoryNotFoundException::new))
                .categoryM(categoryMRepository.findById(requestProductDto.getCategoryMId())
                        .orElseThrow(CategoryNotFoundException::new))
                .categoryL(categoryLRepository.findById(requestProductDto.getCategoryLId())
                        .orElseThrow(CategoryNotFoundException::new))
                .Lname(categoryLRepository.findById(requestProductDto.getCategoryLId())
                        .orElseThrow(CategoryNotFoundException::new).getName())
                .Mname(categoryMRepository.findById(requestProductDto.getCategoryMId())
                        .orElseThrow(CategoryNotFoundException::new).getName())
                .Sname(categorySRepository.findById(requestProductDto.getCategorySId())
                        .orElseThrow(CategoryNotFoundException::new).getName())
                .SSname(categorySSRepository.findById(requestProductDto.getCategorySSId())
                        .orElseThrow(CategoryNotFoundException::new).getName())
                .product(product)
                .build());


        List<OptionInputDto> optionInputDtos = new ArrayList<>();
        for (OptionInputDto optionInputDto : requestProductDto.getOptionInputDtoList()) {
            optionInputDtos.add(OptionInputDto.builder()
                    .colorId(optionInputDto.getColorId())
                    .sizeId(optionInputDto.getSizeId())
                    .stock(optionInputDto.getStock())
                    .build());
        }

        optionInputDtos.forEach(optionInputDto -> {
            Colors colors = null;
            if (optionInputDto.getColorId() != null) {
                colors = colorRepository.findById(optionInputDto.getColorId())
                        .orElseThrow(OptionNotFoundException::new);
            }

            Size size = null;
            if (optionInputDto.getSizeId() != null) {
                size = sizeRepository.findById(optionInputDto.getSizeId())
                        .orElseThrow(OptionNotFoundException::new);
            }

            optionRepository.save(
                    OptionList.builder()
                            .stock(optionInputDto.getStock())
                            .colors(colors)
                            .size(size)
                            .product(product)
                            .build()
            );
        });

        requestProductDto.getInputDetailImgDtoList().forEach
                (createDetailImgDto -> productDetailImgRepository.save(
                        ProductDetailImage.builder()
                                .productDetailImgUrl(createDetailImgDto.getDetailImgUrl())
                                .productDetailImgTxt(createDetailImgDto.getDetailImgTxt())
                                .product(product)
                                .build()
                ));

        requestProductDto.getInputTitleImgDtoList().forEach
                (createTitleImgDto -> productTitleImgRepository.save(
                        ProductTitleImage.builder()
                                .productTitleImgUrl(createTitleImgDto.getTitleImgUrl())
                                .productTitleImgTxt(createTitleImgDto.getTitleImgTxt())
                                .product(product)
                                .build()));

        return product;
    }

    @Override
    public List<OutputSearchProductDto> searchProductByWord(String keyword ) {
        List<Product> productList = productRepository.findFirst20Bykeyword(keyword);
        List<OutputSearchProductDto> outputSearchProductDtos = new ArrayList<>();
        if (productList.isEmpty()) {
            System.out.println("검색 결과가 없습니다");
        } else {
            for (Product product : productList) {
                log.info("df");
                ReviewTotalDto reviewTotalDto = reviewRepository.collectByProductId(product.getId());
                outputSearchProductDtos.add(OutputSearchProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .brand(product.getBrand())
                        .mallTxt(product.getMallText())
                        .oldPrice(product.getOldPrice())
                        .newPrice(product.getNewPrice())
                        .discountRate(product.getDiscountRate())
                        .reviewTotalDto(reviewTotalDto)
                        .thumbnailImgUrl(product.getThumbnailUrl())
                        .priceTxt(product.getPriceText())
                        .regDate(product.getCreateDate())
                        .build());


            }
        }

        return outputSearchProductDtos;
    }

    @Override
    public List<ResponseProductDto> getAll() {
        List<Product> ListProduct = productRepository.findAll();
        List<ResponseProductDto> responseProductDtoList = new ArrayList<>();

        ListProduct.forEach(product -> {
            List<ProductDetailImage> detailImageList = productDetailImgRepository.findAllByProduct(product);
            List<OutputDetailImgDto> detailDtoList = new ArrayList<>();

            for (ProductDetailImage detailImage : detailImageList) {
                detailDtoList.add(OutputDetailImgDto.builder()
                        .productDetailImgTxt(detailImage.getProductDetailImgTxt())
                        .productDetailImgUrl(detailImage.getProductDetailImgUrl())
                        .build());
            }

            List<OutputTitleImgDto> titleDtoList = new ArrayList<>();
            List<ProductTitleImage> titleImageList = productTitleImgRepository.findAllByProduct(product);

            for (ProductTitleImage productTitleImage : titleImageList) {
                titleDtoList.add(OutputTitleImgDto.builder()
                        .productTitleImgTxt(productTitleImage.getProductTitleImgTxt())
                        .productTitleImgUrl(productTitleImage.getProductTitleImgUrl())
                        .build());
            }

            List<OptionOutputDto> optionOutputDtoList = new ArrayList<>();
            List<OptionList> optionList = optionRepository.findAllByProduct(product);
            for (OptionList option : optionList) {
                optionOutputDtoList.add(OptionOutputDto.builder()
                        .id(option.getId())
                        .color(option.getColors().getName())
                        .size(option.getSize().getType())
                        .colorId(option.getColors().getId())
                        .sizeId(option.getSize().getId())
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
                    .oldPrice(product.getOldPrice())
                    .newPrice(product.getNewPrice())
                    .discountRate(product.getDiscountRate())
                    .priceText(product.getPriceText())
                    .mallTxt(product.getMallText())
                    .productBrand(product.getBrand())
                    .sellAmount(product.getSellAmt())
                    .explanation(product.getExplanation())
                    .thumbnailImgUrl(product.getThumbnailUrl())
                    .pofCategoryLList(categoryLlist)
                    .pofCategoryMList(categoryMList)
                    .pofCategorySList(categorySList)
                    .pofCategorySSList(categorySSList)
                    .outputDetailImgDtos(detailDtoList)
                    .outputTitleImgDtos(titleDtoList)
                    .optionOutputDtos(optionOutputDtoList)
                    .regDate(product.getCreateDate())
                    .build());
        });
        return responseProductDtoList;
    }

    @Override
    public ResponseRecommendProductDto getRecommendProductById(Long id) {
        Product recproduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        ReviewTotalDto reviewTotalDto = reviewRepository.collectByProductId(id);
        return ResponseRecommendProductDto.builder()
                .id(recproduct.getId())
                .name(recproduct.getName())
                .mallText(recproduct.getMallText())
                .brand(recproduct.getBrand())
                .priceText(recproduct.getPriceText())
                .oldPrice(recproduct.getOldPrice())
                .newPrice(recproduct.getNewPrice())
                .discountRate(recproduct.getDiscountRate())
                .reviewTotalDto(reviewTotalDto)
                .titleImgUrl(recproduct.getThumbnailUrl())
                .regDate(recproduct.getCreateDate())
                .build();
    }


    @Override
    public ResponseProductDto getByProductId(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        List<ProductDetailImage> detailImageList = productDetailImgRepository.findAllByProduct(product);
        List<OutputDetailImgDto> detailDtoList = new ArrayList<>();
        List<Review> reviewList = reviewRepository.findFirst5ByProductId(id);
        ReviewTotalDto reviewTotalDto = reviewRepository.collectByProductId(product.getId());
        Long qnaCount = qnaRepository.countByProductId(id);
        List<ResponseProductReviewDto> responseProductReviewDtoList = new ArrayList<>();
        reviewList.forEach(review -> {
            List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReviewId(review.getId());
            List<OutputReviewImgDto> outputReviewImgDtos = new ArrayList<>();
            reviewImageList.forEach(reviewImage -> outputReviewImgDtos.add(OutputReviewImgDto.builder()
                    .reviewImgUrl(reviewImage.getReviewImgUrl())
                    .reviewImgTxt(reviewImage.getReviewImgTxt())
                    .build()));
            responseProductReviewDtoList.add(
                    ResponseProductReviewDto.builder()
                            .reviewId(review.getId())
                            .updateDate(review.getUpdatedDate())
                            .regDate(review.getCreateDate())
                            .orderDetailId(review.getOrderDetailId())
                            .reviewContent(review.getReviewContent())
                            .outputReviewImgDtos(outputReviewImgDtos)
                            .build());
        });

        List<QnA> qnAList = qnaRepository.findFirst5ByProductId(id);
        List<ResponseProductQnaDto> responseProductQnaDtoList = new ArrayList<>();
        qnAList.forEach(qnA -> responseProductQnaDtoList.add(ResponseProductQnaDto.builder()
                .productId(qnA.getProduct().getId())
                .userLoginId(qnA.getUser().getUserId())
                .qnaType(qnA.getQnaType())
                .qnaId(qnA.getId())
                .qnaContent(qnA.getQnaContent())
                .qnaTitle(qnA.getQnaTitle())
                .isSecret(qnA.getIsSecret())
                .updateDate(qnA.getUpdatedDate())
                .regDate(qnA.getCreateDate())
                .build()));

        for (ProductDetailImage detailImage : detailImageList) {
            detailDtoList.add(OutputDetailImgDto.builder()
                    .productDetailImgTxt(detailImage.getProductDetailImgTxt())
                    .productDetailImgUrl(detailImage.getProductDetailImgUrl())
                    .build());
        }

        List<ProductTitleImage> titleImageList = productTitleImgRepository.findAllByProduct(product);
        List<OutputTitleImgDto> titleDtoList = new ArrayList<>();

        for (ProductTitleImage productTitleImage : titleImageList) {
            titleDtoList.add(OutputTitleImgDto.builder()
                    .productTitleImgTxt(productTitleImage.getProductTitleImgTxt())
                    .productTitleImgUrl(productTitleImage.getProductTitleImgUrl())
                    .build());
        }

        List<OptionOutputDto> optionOutputDtoList = new ArrayList<>();
        List<OptionList> optionList = optionRepository.findAllByProduct(product);

        for (OptionList option : optionList) {
            log.info(Logger.ROOT_LOGGER_NAME);

            optionOutputDtoList.add(OptionOutputDto.builder()
                    .id(option.getId())
                    .color(option.getColors().getName())
                    .size(option.getSize().getType())
                    .colorId(option.getColors().getId())
                    .sizeId(option.getSize().getId())
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
                .oldPrice(product.getOldPrice())
                .newPrice(product.getNewPrice())
                .discountRate(product.getDiscountRate())
                .productBrand(product.getBrand())
                .priceText(product.getPriceText())
                .mallTxt(product.getMallText())
                .thumbnailImgUrl(product.getThumbnailUrl())
                .sellAmount(product.getSellAmt())
                .reviewStatic(reviewTotalDto)
                .someOfReview(responseProductReviewDtoList)
                .someOfQnA(responseProductQnaDtoList)
                .qnaCount(qnaCount)
                .explanation(product.getExplanation())
                .pofCategoryLList(categoryLlist)
                .pofCategoryMList(categoryMList)
                .pofCategorySList(categorySList)
                .pofCategorySSList(categorySSList)
                .outputDetailImgDtos(detailDtoList)
                .outputTitleImgDtos(titleDtoList)
                .optionOutputDtos(optionOutputDtoList)
                .regDate(product.getCreateDate())
                .build();
    }

    @Override
    public Product editProductById(UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(updateProductDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        productRepository.save(
                Product.builder()
                        .id(updateProductDto.getProductId())
                        .name(updateProductDto.getProductName())
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


