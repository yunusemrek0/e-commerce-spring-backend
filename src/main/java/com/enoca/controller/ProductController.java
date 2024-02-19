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

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //create product
    @PostMapping("/save") //http://localhost:8080/product/save
    public ResponseEntity<ResponseMessage<ProductResponse>> saveProduct(
            @RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.saveProduct(productRequest));
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

    @GetMapping("/getProductById/{id}") // http://localhost:8080/products/getProductByName?name=Shoes
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
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

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }


}
