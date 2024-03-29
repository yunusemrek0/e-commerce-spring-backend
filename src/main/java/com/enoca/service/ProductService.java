package com.enoca.service;

import com.enoca.entity.CartItem;
import com.enoca.entity.Product;
import com.enoca.exception.BadRequestException;
import com.enoca.exception.ConflictException;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.mapper.ProductMapper;
import com.enoca.message.ErrorMessages;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.ProductRequest;
import com.enoca.payload.response.ProductResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.ProductRepository;
import com.enoca.service.helper.ProductHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductHelper productHelper;
    public ResponseMessage<ProductResponse> saveProduct(ProductRequest productRequest) {
        String name = productRequest.getName();
        boolean isExistsName = productRepository.existsByName(name);
        if (isExistsName){
            throw new ConflictException(String.format(ErrorMessages.ALREADY_CREATED_PRODUCT_NAME_MESSAGE, name));
        }
        Product product = productMapper.mapProductRequestToProduct(productRequest);
        Product savedProduct = productRepository.save(product);

        return ResponseMessage.<ProductResponse>builder()
                .message(SuccessMessages.PRODUCT_CREATE)
                .returnBody(productMapper.mapProductToProductResponse(savedProduct))
                .build();
    }


    public Page<ProductResponse> getProductsByPage(int page, int size, String sort, String type) {

        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        if(Objects.equals(type,"desc")){
            pageable = PageRequest.of(page,size, Sort.by(sort).descending());
        }
        return productRepository.findAll(pageable).map(productMapper::mapProductToProductResponse);

    }


    public List<ProductResponse> getProductByName(String name) {
        List<Product> products = productRepository.getByNameContaining(name);

        if (products.isEmpty()){
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND_PRODUCT_MESSAGE);
        }


        return products
                .stream()
                .map(productMapper::mapProductToProductResponse)
                .collect(Collectors.toList());
    }

    public ResponseMessage<ProductResponse> updateProduct(ProductRequest productRequest, Long id) {
        //check product if really exist
        Product product =productHelper.existById(id);
        boolean isExistsName = productRepository.existsByName(productRequest.getName());
        boolean areNamesSame = Objects.equals(productRequest.getName(), product.getName());
        if (isExistsName && !areNamesSame){
            throw new ConflictException(String.format(
                    ErrorMessages.ALREADY_CREATED_PRODUCT_NAME_MESSAGE, productRequest.getName()));
        }

        Product productToUpdate = productMapper.mapProductRequestToProduct(productRequest);
        productToUpdate.setId(product.getId());
        Product updatedProduct = productRepository.save(productToUpdate);

        return ResponseMessage.<ProductResponse>builder()
                .message(SuccessMessages.PRODUCT_UPDATE_MESSAGE)
                .httpStatus(HttpStatus.OK)
                .returnBody(productMapper.mapProductToProductResponse(updatedProduct))
                .build();
    }



    public String deleteProductById(Long id) {
        productHelper.existById(id);
        productRepository.deleteById(id);
        return SuccessMessages.PRODUCT_DELETE;
    }

    public void decreasingProductQuantity(List<CartItem> cartItems){

        for (CartItem cartItem:cartItems){
            Product product = productHelper.existById(cartItem.getProduct().getId());
            Integer quantity = product.getStockQuantity();
            Integer sold = cartItem.getQuantity();
            product.setStockQuantity(quantity-sold);
            productRepository.save(product);

        }
    }

    public void isAvaliableForSale(List<CartItem> cartItems){

        for (CartItem cartItem:cartItems){
            if (cartItem.getProduct().getStockQuantity()<cartItem.getQuantity()){
                throw new BadRequestException(ErrorMessages.NOT_ENOUGH_PRODUCT_QUANTITY);
            }
        }
    }


    public ProductResponse getProductById(Long id) {
        return productMapper.mapProductToProductResponse(productHelper.existById(id));
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(productMapper::mapProductToProductResponse).collect(Collectors.toList());
    }
}
