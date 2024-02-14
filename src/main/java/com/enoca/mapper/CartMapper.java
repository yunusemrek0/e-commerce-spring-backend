package com.enoca.mapper;

import com.enoca.entity.Cart;
import com.enoca.payload.request.CartRequest;
import com.enoca.payload.response.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;


    public Cart mapCartRequestToCart(CartRequest cartRequest){
        return null;

    }

    public CartResponse mapCartToCartResponse(Cart cart){

        return CartResponse.builder()
                .customerName(cart.getCustomer().getName())
                .totalPrice(cart.getTotalPriceInCart())

                .cartItems(cart.getCartItem()
                        .stream()
                        .map(cartItemMapper::mapCartItemToCartItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}
