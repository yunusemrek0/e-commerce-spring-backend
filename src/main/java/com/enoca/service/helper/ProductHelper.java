package com.enoca.service.helper;

import com.enoca.entity.Product;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.message.ErrorMessages;
import com.enoca.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductHelper {

    private final ProductRepository productRepository;

    public Product existById(Long id){
        return productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_PRODUCT_ID_MESSAGE, id))
        );
    }

}
