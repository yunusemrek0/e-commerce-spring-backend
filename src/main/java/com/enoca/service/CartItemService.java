package com.enoca.service;

import com.enoca.entity.Cart;
import com.enoca.entity.CartItem;
import com.enoca.entity.Order;
import com.enoca.entity.Product;
import com.enoca.mapper.CartItemMapper;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.CartItemRepository;
import com.enoca.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CartRepository cartRepository;


    public ResponseMessage<CartItemResponse> saveCartItem(CartItemRequest cartItemRequest,Long cartId) {

        Product product = productService.existById(cartItemRequest.getProductId());
        Cart cart = cartService.existById(cartId);

        CartItem cartItem = cartItemMapper
                .mapCartItemRequestToCartItem(cartItemRequest,product,cart);

        CartItem cartItemToSave = cartItemRepository.save(cartItem);


        cart.getCartItem().add(cartItemToSave);
        cartRepository.save(cart);


        return ResponseMessage.<CartItemResponse> builder()
                .message(SuccessMessages.CART_ITEM_ADDED)
                .returnBody(cartItemMapper.mapCartItemToCartItemResponse(cartItemToSave))
                .build();


    }

    public void deleteCartItemByCartId(Long cartId){

        List<CartItem> cartItems = cartItemRepository.getByCartIdAndOrderNull(cartId);

        for (CartItem cartItem:cartItems){
            cartItemRepository.deleteById(cartItem.getId());
        }
    }

    public String emptyCart(Long id) {

        Cart cart = cartService.existById(id);
        deleteCartItemByCartId(id);


        cart.setCartItem(new ArrayList<>());
        cart.setTotalPriceInCart(0.00);


        cartRepository.save(cart);

        return SuccessMessages.CART_EMPTY;


    }

    public List<CartItem> getByCartId(Long cartId){
        return cartItemRepository.getByCartIdAndOrderNull(cartId);
    }

    public void updateCartItemOrder(Order order,List<CartItem> cartItems){

        for (CartItem cartItem:cartItems){
            cartItem.setOrder(order);
            cartItemRepository.save(cartItem);
        }


    }


    public void makeEmptyToCart(List<CartItem> cartItems) {

        Cart cart = cartItems.get(0).getCart();
        cart.setTotalPriceInCart(0.0);
        cartRepository.save(cart);

        for (CartItem cartItem:cartItems){
            cartItem.setCart(null);
            cartItemRepository.save(cartItem);
        }
    }

    public List<CartItem> getByOrderId(Long orderId){
        return cartItemRepository.getByOrderId(orderId);
    }


    public List<CartItem> getByOrderIdSet(Set<Long> orderIds) {

        return cartItemRepository.getByOrderIdSet(orderIds);
    }
}
