package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.category.entity.*;
import com.spharosacademy.project.SSGBack.category.exception.CategoryNotFoundException;
import com.spharosacademy.project.SSGBack.category.repository.*;
import com.spharosacademy.project.SSGBack.order.exception.OrderedProductNotFound;
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
import com.spharosacademy.project.SSGBack.product.exception.UserNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.dto.input.OptionInputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ColorOutputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.OptionOutputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.SizeOutputDto;
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
import com.spharosacademy.project.SSGBack.recentWatch.entity.RecentWatchProduct;
import com.spharosacademy.project.SSGBack.recentWatch.entity.RecentWatchQuery;
import com.spharosacademy.project.SSGBack.recentWatch.repository.RecentWatchProductRepository;
import com.spharosacademy.project.SSGBack.recentWatch.repository.RecentWatchQueryRepository;
import com.spharosacademy.project.SSGBack.review.dto.output.OutputReviewImgDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ResponseProductReviewImageDto;
import com.spharosacademy.project.SSGBack.review.dto.output.ReviewTotalDto;
import com.spharosacademy.project.SSGBack.review.entity.Review;
import com.spharosacademy.project.SSGBack.review.image.entity.ReviewImage;
import com.spharosacademy.project.SSGBack.review.image.repo.ReviewImageRepository;
import com.spharosacademy.project.SSGBack.review.repo.ReviewRepository;
import com.spharosacademy.project.SSGBack.s3.dto.DetailImageS3Dto;
import com.spharosacademy.project.SSGBack.s3.dto.ReviewImageS3Dto;
import com.spharosacademy.project.SSGBack.s3.dto.S3ProductImageDto;
import com.spharosacademy.project.SSGBack.s3.service.S3UploaderService;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final RecentWatchProductRepository recentWatchProductRepository;
    private final RecentWatchQueryRepository recentWatchQueryRepository;
    private final S3UploaderService s3UploaderService;

    @Override

    @Transactional(rollbackFor = {Exception.class, OptionNotFoundException.class})
    public Product addProduct(RequestProductDto requestProductDto, MultipartFile multipartFile,
                              List<MultipartFile> detailFileList, List<MultipartFile> titleFileList) {

        Product product = null;

        List<OptionInputDto> optionInputDtos = new ArrayList<>();
        for (OptionInputDto optionInputDto : requestProductDto.getOptionInputDtoList()) {
            optionInputDtos.add(OptionInputDto.builder()
                    .colorId(optionInputDto.getColorId())
                    .sizeId(optionInputDto.getSizeId())
                    .stock(optionInputDto.getStock())
                    .build());
        }

        Product finalProduct = product;
        optionInputDtos.forEach(optionInputDto -> {
            if (!colorRepository.existsById(optionInputDto.getColorId()) || !sizeRepository.existsById(optionInputDto.getSizeId())) {
                throw new OptionNotFoundException();
            }
            Colors colors = null;
            if (optionInputDto.getColorId() != null) {
                colors = colorRepository.findById(optionInputDto.getColorId()).orElseThrow(OptionNotFoundException::new);
            }

            Size size = null;
            if (optionInputDto.getSizeId() != null) {
                size = sizeRepository.findById(optionInputDto.getSizeId()).orElseThrow(OptionNotFoundException::new);
            }

            optionRepository.save(
                    OptionList.builder()
                            .stock(optionInputDto.getStock())
                            .colors(colors)
                            .size(size)
                            .product(finalProduct)
                            .build()
            );

            productRepository.save(
                    Product.builder()
                            .name(requestProductDto.getName())
                            .priceText(requestProductDto.getPriceText())
                            .mallText(requestProductDto.getMallTxt())
                            .oldPrice(requestProductDto.getOldPrice())
                            .discountRate(requestProductDto.getDiscountRate())
                            .newPrice(requestProductDto.getOldPrice() * (1 - requestProductDto.getDiscountRate()))
                            .brand(requestProductDto.getBrand())
                            .sellAmt(requestProductDto.getSellAmount())
                            .explanation(requestProductDto.getExplanation())
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
        });

        S3ProductImageDto s3ProductImageDto;
        DetailImageS3Dto detailImageS3Dto;

        try {
            s3ProductImageDto = s3UploaderService.upload(multipartFile, "myspharosbucket", "myDir");
            product.setThumbnailUrl(s3ProductImageDto.getImageUrl());

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (MultipartFile titleFiles : titleFileList) {
            try {
                detailImageS3Dto = s3UploaderService.uploadDetails(titleFiles, "myspharosbucket", "myDir");

                productTitleImgRepository.save(ProductTitleImage.builder()
                        .product(product)
                        .productTitleImgUrl(detailImageS3Dto.getImageUrl())
                        .productTitleImgTxt(detailImageS3Dto.getSaveFileName())
                        .build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.info("df");

        for (MultipartFile multipartFiles : detailFileList) {
            try {
                detailImageS3Dto = s3UploaderService.uploadDetails(multipartFiles, "myspharosbucket", "myDir");

                productDetailImgRepository.save(ProductDetailImage.builder()
                        .product(product)
                        .productDetailImgUrl(detailImageS3Dto.getImageUrl())
                        .productDetailImgTxt(detailImageS3Dto.getSaveFileName())
                        .build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Product finalProduct1 = product;
        try {
            optionInputDtos.forEach(optionInputDto -> {
                Colors colors = null;
                if (optionInputDto.getColorId() != null) {
                    colors = colorRepository.findById(optionInputDto.getColorId()).orElseThrow(OptionNotFoundException::new);
                }

                Size size = null;
                if (optionInputDto.getSizeId() != null) {
                    size = sizeRepository.findById(optionInputDto.getSizeId()).orElseThrow(OptionNotFoundException::new);
                }

                optionRepository.save(
                        OptionList.builder()
                                .stock(optionInputDto.getStock())
                                .colors(colors)
                                .size(size)
                                .product(finalProduct1)
                                .build()
                );


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;

    }

    @Override
    public Page<OutputSearchProductDto> searchProductByWord(String query, Long userid, Pageable pageable) {
        Page<Product> productPage = productRepository.searchBysearchWord(query, pageable);
        User user = null;
        if (productPage.isEmpty()) {
            System.out.println("검색 결과가 없습니다");
        }
        Long recentId = recentWatchQueryRepository.existsByUserAndQuery(userid, query);
        if (userid != -1) {
            if (recentId == null) {
                recentWatchQueryRepository.save(RecentWatchQuery.builder()
                        .user(userRepository.findById(userid).orElseThrow(UserNotFoundException::new))
                        .query(query)
                        .build());
            }
        } else {
            user = null;

        }


        return productPage.map(product -> {
            ReviewTotalDto reviewTotalDto = reviewRepository.collectByProductId(product.getId());
            Long duplicate;
            Long wishId;
            if (userid != -1) {
                duplicate = wishListRepository.findByUserIdAndProductId(userid, product.getId());
                if (duplicate == null) {
                    wishId = null;
                } else {
                    wishId = duplicate;
                }
            } else {
                wishId = null;
            }
            return OutputSearchProductDto.builder()
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
                    .wishId(wishId)
                    .build();
        });

    }

    @Override
    public List<ColorOutputDto> getProductColor(Long id) {
        return optionRepository.getColorId(id);
    }

    @Override
    public List<SizeOutputDto> getProductSize(Long productId, Long colorId) {
        return optionRepository.getSizeId(productId, colorId);
    }

    @Override
    public void addImages(Long id, List<MultipartFile> multipartFileList) {
        Product product = productRepository.findById(id).get();

        ReviewImageS3Dto reviewImageS3Dto;
        for (MultipartFile multipartFiles : multipartFileList) {
            try {
                reviewImageS3Dto = s3UploaderService.uploadReviewImage(multipartFiles, "myspharosbucket", "myDir");

                productTitleImgRepository.save(ProductTitleImage.builder()
                        .product(product)
                        .productTitleImgUrl(reviewImageS3Dto.getImageUrl())
                        .productTitleImgTxt(reviewImageS3Dto.getSaveFileName())
                        .build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    .regDate(product.getCreateDate())
                    .build());
        });
        return responseProductDtoList;
    }

    @Override
    public ResponseRecommendProductDto getRecommendProductById(Long id, Long userid) {
        Product recproduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        ReviewTotalDto reviewTotalDto = reviewRepository.collectByProductId(id);

        Long duplicate;
        Long wishId;

        if (userid != -1) {
            duplicate = wishListRepository.findByUserIdAndProductId(userid, id);
            if (duplicate == null) {
                wishId = null;
            } else {
                wishId = duplicate;
            }
        } else {
            wishId = null;
        }

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
                .wishId(wishId)
                .build();
    }


    @Override
    public ResponseProductDto getByProductId(Long id, Long userid) {
        Long duplicate;
        Long wishId;

        User user = null;
        //회원이 상품을 조회한 경우
        if (userid != -1) {
            Long recentProductId = recentWatchProductRepository.findByUserIdAndProductId(userid, id);
            User users = userRepository.findById(userid).orElseThrow(UserNotFoundException::new);
            if (recentProductId == null) {
                recentWatchProductRepository.save(RecentWatchProduct.builder()
                        .user(users)
                        .product(productRepository.findById(id).orElseThrow(ProductNotFoundException::new))
                        .build());
            }
            user = userRepository.findById(userid).orElseThrow(UserNotFoundException::new);
            duplicate = wishListRepository.findByUserIdAndProductId(userid, id);
            if (duplicate == null) {
                wishId = null;
            } else {
                wishId = duplicate;
            }
            //비회원이 상품을 조회한 경우
        } else {
            user = null;
            wishId = null;
        }

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
                            .reviewScore(review.getReviewScore())
                            .userLoginId(userRepository.findById(review.getUser().getId()).get().getUserId())
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


        List<ReviewImage> reviewImageList = reviewImageRepository.findFirst8ByProductId(id);
        List<ResponseProductReviewImageDto> responseProductReviewImageDtos = new ArrayList<>();
        reviewImageList.forEach(reviewImage ->
                responseProductReviewImageDtos.add(ResponseProductReviewImageDto.builder()
                        .reviewImgUrl(reviewImage.getReviewImgUrl())
                        .build()));

        List<ColorOutputDto> colorId = optionRepository.getColorId(id);
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
                .colorOutputDtos(colorId)
                .reviewStatic(reviewTotalDto)
                .someOfReview(responseProductReviewDtoList)
                .someOfQnA(responseProductQnaDtoList)
                .qnaCount(qnaCount)
                .explanation(product.getExplanation())
                .pofCategoryLList(categoryLlist)
                .pofCategoryMList(categoryMList)
                .responseProductReviewImageDtos(responseProductReviewImageDtos)
                .pofCategorySList(categorySList)
                .pofCategorySSList(categorySSList)
                .outputDetailImgDtos(detailDtoList)
                .outputTitleImgDtos(titleDtoList)
                .regDate(product.getCreateDate())
                .wishId(wishId)
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


