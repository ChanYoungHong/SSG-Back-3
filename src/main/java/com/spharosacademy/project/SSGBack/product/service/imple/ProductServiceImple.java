package com.spharosacademy.project.SSGBack.product.service.imple;

import com.spharosacademy.project.SSGBack.product.dto.input.UpdateProductDto;
import com.spharosacademy.project.SSGBack.product.dto.output.ResponseProductDto;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestProductDto;
import com.spharosacademy.project.SSGBack.product.repository.CategorySSRepository;
import com.spharosacademy.project.SSGBack.product.repository.ProductRepository;
import com.spharosacademy.project.SSGBack.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImple implements ProductService {

    private final CategorySSRepository categorySSRepository;
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(RequestProductDto requestProductDto) {
        productRepository.save(
                Product.builder()
                        .productName(requestProductDto.getProductName())
                        .price(requestProductDto.getPrice())
                        .productBrand(requestProductDto.getProductBrand())
                        .productColor(requestProductDto.getProductColor())
                        .productCnt(requestProductDto.getProductCnt())
                        .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
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
    public ResponseProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setPrice(product.getPrice());
        responseProductDto.setProductName(product.getProductName());
        responseProductDto.setProductCnt(product.getProductCnt());
        responseProductDto.setProductBrand(product.getProductBrand());
        responseProductDto.setProductId(product.getProductId());
        responseProductDto.setProductColor(product.getProductColor());
        responseProductDto.setCategorySSId(product.getCategorySS().getId());
        responseProductDto.setSellAmount(product.getProductSellAmt());
        return responseProductDto;
    }

    @Override
    public UpdateProductDto editProductById(UpdateProductDto updateProductDto) throws Exception {
        Optional<Product> product = productRepository.findById(updateProductDto.getProductId());
        UpdateProductDto updateDto = new UpdateProductDto();
        if (product.isPresent()) {
//            productRepository.save(
//                    Product.builder()
//                            .productId(updateProductDto.getProductId())
//                            .productName(requestProductDto.getProductName())
//                            .price(requestProductDto.getPrice())
//                            .productBrand(requestProductDto.getProductBrand())
//                            .productColor(requestProductDto.getProductColor())
//                            .productCnt(requestProductDto.getProductCnt())
//                            .categorySS(categorySSRepository.findById(requestProductDto.getCategorySSId()).get())
//                            .build()
//            );
            updateDto.setProductId(updateProductDto.getProductId());
            updateDto.setProductName(updateProductDto.getProductName());
            updateDto.setPrice(updateProductDto.getPrice());
            updateDto.setProductBrand(updateProductDto.getProductBrand());
            updateDto.setProductColor(updateProductDto.getProductColor());
            updateDto.setProductCnt(updateProductDto.getProductCnt());
            updateDto.setCategorySSId(updateProductDto.getCategorySSId());
            return updateDto;
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
