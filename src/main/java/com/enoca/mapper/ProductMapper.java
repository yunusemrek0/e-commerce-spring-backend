package com.enoca.mapper;

import com.enoca.entity.Customer;
import com.enoca.entity.Product;
import com.enoca.payload.request.CustomerRequest;
import com.enoca.payload.request.ProductRequest;
import com.enoca.payload.response.CustomerResponse;
import com.enoca.payload.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public ProductResponse mapProductToProductResponse(Product product){
        /*
        ProductResponse productResponse = new ProductResponse();

        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());

        return productResponse;

         */

        return ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .creationDate(product.getCreationDate())
                .build();
    }

    public Product mapProductRequestToProduct(ProductRequest productRequest){

        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .creationDate(LocalDateTime.now())
                .build();
    }


}
