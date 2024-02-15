package com.enoca.controller;

import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/save/{cartId}") //http://localhost:8080/cartItems/save
    public ResponseEntity<ResponseMessage<CartItemResponse>> saveCartItem(
            @RequestBody @Valid CartItemRequest cartItemRequest,
            @PathVariable Long cartId){
        return ResponseEntity.ok(cartItemService.saveCartItem(cartItemRequest,cartId));
    }



}
