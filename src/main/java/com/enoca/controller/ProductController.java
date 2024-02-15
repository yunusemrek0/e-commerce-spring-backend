package com.enoca.controller;


import com.enoca.payload.request.ProductRequest;
import com.enoca.payload.response.ProductResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //create product
    @PostMapping("/createProduct") //http://localhost:8080/products/createProduct
    public ResponseEntity<ResponseMessage<ProductResponse>> createProduct(
            @RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    // get all products by page
    @GetMapping("/getAllProductsByPage") // http://localhost:8080/products/getAllProductsByPage?page=0&size=2&sort=name&type=ASC
    public ResponseEntity<Page<ProductResponse>> getProductsByPage(
            @RequestParam (value = "page", defaultValue = "0") int page,
            @RequestParam (value = "size", defaultValue = "2") int size,
            @RequestParam (value = "sort", defaultValue = "name") String sort,
            @RequestParam (value = "type", defaultValue = "desc") String type){

        return ResponseEntity.ok(productService.getProductsByPage(page,size,sort,type));
    }

    // get product by name
    @GetMapping("/getProductByName") // http://localhost:8080/products/getProductByName?name=Shoes
    public ResponseEntity<List<ProductResponse>> getProductByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    // update product
    @PutMapping("/updateProduct/{id}") // http://localhost:8080/products/updateProduct/2 + PUT
    public ResponseMessage<ProductResponse>updateProduct(
            @RequestBody @Valid ProductRequest productRequest, @PathVariable Long id){
        return productService.updateProduct(productRequest, id);
    }


    // delete product
    @DeleteMapping("/deleteProduct/{id}") // http://localhost:8080/products/deleteProduct/1 + DELETE
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }


}
