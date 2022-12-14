package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.*;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.exception.OptionNotFoundException;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ColorOutputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.SizeOutputDto;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public String addProduct(
            @RequestPart(value = "productRequestDto") RequestProductDto requestProductDto,
            @RequestPart(value = "thumbnailImg", required = false) MultipartFile multipartFile,
            @RequestPart(value = "detailImages", required = false) List<MultipartFile> multipartFileList,
            @RequestPart(value = "titleImages", required = false) List<MultipartFile> titleFileList) throws IOException {
        productService.addProduct(requestProductDto, multipartFile, multipartFileList, titleFileList);
        return "?????? ????????? ?????????????????????";
    }

    @PostMapping("/images/{id}")
    public String addImages(@PathVariable Long id,
                            @RequestPart(value = "reviewImages", required = false) List<MultipartFile> multipartFileList) {
        productService.addImages(id, multipartFileList);
        return "?????? ?????? ??????";
    }

    //?????? ???????????? ?????? ?????? ??????
    @GetMapping("/getall")
    public ResponseEntity<List<ResponseProductDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAll());
    }

    //?????? ?????? ??????
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) throws Exception {
        productService.deleteProductById(id);
        return "?????? ????????? ?????????????????????";
    }

    //?????? ?????? ??????(??????)
    @GetMapping("/user/get/{id}")
    public ResponseProductDto getProductById(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return productService.getByProductId(id, userId);
    }

    @GetMapping("/nonmember/get/{id}")
    public ResponseProductDto getProduct(@PathVariable Long id) {
        Long userId = -1L;
        return productService.getByProductId(id, userId);
    }

    @GetMapping("/color/{id}")
    public List<ColorOutputDto> getProductColor(@PathVariable Long id) {
        return productService.getProductColor(id);
    }

    @GetMapping("/size/{productId}/{colorId}")
    public List<SizeOutputDto> getSizeAndStock(@PathVariable Long productId, @PathVariable Long colorId) {
        return productService.getProductSize(productId, colorId);
    }

    //?????? ?????? ?????? 
    @PutMapping("/update")
    public String editProduct
    (@RequestBody UpdateProductDto updateProductDto) throws Exception {
        productService.editProductById(updateProductDto);
        return "?????? ????????? ?????????????????????";
    }

    //?????? ???????????? ???????????? ?????? ?????? - ??????
    @GetMapping("/user/recommend/{id}")
    public ResponseRecommendProductDto getRecommendProductById(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return productService.getRecommendProductById(id, userId);
    }

    @GetMapping("/nonmember/recommend/{id}")
    public ResponseRecommendProductDto getRecommendProductByNonMember(@PathVariable Long id) {
        Long userid = -1L;
        return productService.getRecommendProductById(id, userid);
    }

    //?????? ?????? - ??????
    @GetMapping("/user/search")
    public Page<OutputSearchProductDto> SearchProductByWord(@RequestParam String query, Pageable pageable) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return productService.searchProductByWord(query, userId, pageable);
    }

    @GetMapping("/nonmember/search")
    public Page<OutputSearchProductDto> searchProductByKeyword(@RequestParam String query,
                                                               @PageableDefault(size = 30) Pageable pageable) {

        Long userId = -1L;
        return productService.searchProductByWord(query, userId, pageable);
    }

}
