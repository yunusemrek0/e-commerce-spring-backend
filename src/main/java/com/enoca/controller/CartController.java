package com.enoca.controller;

import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.request.CartRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.CartResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.service.CartItemService;
import com.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;




    @GetMapping("/getByCustomerId/{customerId")
    public CartResponse getByCustomerId(@PathVariable Long customerId){
        return cartService.getByCustomerId(customerId);
    }


    @PutMapping("/empty/{id}")
    public ResponseEntity<String> emptyCart(@PathVariable Long id){
        return ResponseEntity.ok(cartItemService.emptyCart(id));
    }

    @PatchMapping("/addProductToCart/{cartId}")
    public ResponseMessage<CartItemResponse> addCartItemToCart(@PathVariable Long cartId,
                                                              @RequestBody @Valid CartItemRequest cartItemRequest){

        return cartService.addCartItemToCart(cartId,cartItemRequest);
    }


    @DeleteMapping("/removeCartItemToCart/{cartId}")
    public ResponseEntity<String> removeCartItemToCart(@PathVariable Long cartId,
                                                               @RequestParam Long cartItemId){

        return ResponseEntity.ok(cartService.removeCartItemToCart(cartId,cartItemId));
    }








}
