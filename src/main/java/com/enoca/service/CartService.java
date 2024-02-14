package com.enoca.service;

import com.enoca.entity.Cart;
import com.enoca.entity.CartItem;
import com.enoca.entity.Customer;
import com.enoca.entity.Product;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.mapper.CartItemMapper;
import com.enoca.mapper.CartMapper;
import com.enoca.message.ErrorMessages;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.request.CartRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.CartResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.CartItemRepository;
import com.enoca.repository.CartRepository;
import com.enoca.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final CartMapper cartMapper;
    private final CustomerRepository customerRepository;
    private final ProductService productService;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;



    public ResponseMessage<CartResponse> saveCart(CartRequest cartRequest) {

        Customer customer = customerService.existsById(cartRequest.getCustomerId());

        Cart cart = new Cart();
        cart.setTotalPriceInCart(0.00);
        cart.setCustomer(customer);


        Cart savedCart=cartRepository.save(cart);

        customer.setCart(savedCart);
        customerRepository.save(customer);



        return ResponseMessage.<CartResponse>builder()
                .message(SuccessMessages.CART_CREATE)
                .returnBody(cartMapper.mapCartToCartResponse(savedCart))
                .build();

    }


    public Cart existById(Long id){
        return cartRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CART_ID,id))
        );
    }


    public CartResponse getByCustomerId(Long customerId) {

        Customer customer = customerService.existsById(customerId);

        return cartMapper.mapCartToCartResponse(customer.getCart());
    }



    public ResponseMessage<CartItemResponse> addCartItemToCart(Long cartId, CartItemRequest cartItemRequest) {
        Cart cart = existById(cartId);
        Product product = productService.existById(cartItemRequest.getProductId());

        CartItem cartItem = cartItemMapper.mapCartItemRequestToCartItem(cartItemRequest,product,cart);

        cart.getCartItem().add(cartItem);
        cart.setTotalPriceInCart(
                cartTotalPriceCalculator(cart.getCartItem()));

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return ResponseMessage.<CartItemResponse> builder()
                .message(SuccessMessages.CART_ITEM_ADDED)
                .returnBody(cartItemMapper.mapCartItemToCartItemResponse(cartItem))
                .httpStatus(HttpStatus.OK)
                .build();



    }

    public Double cartTotalPriceCalculator(List<CartItem> cartItems){

        double totalPrice=0.0;

        for (CartItem cartItem:cartItems){
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return totalPrice;
    }

    public String removeCartItemToCart(Long cartId, Long cartItemId) {
        Cart cart = existById(cartId);

        List<CartItem> cartItems = cart.getCartItem();

        for (CartItem cartItem:cartItems){

            if (cartItem.getId().equals(cartItemId)){

                cartItems.remove(cartItem);
                cartItemRepository.deleteById(cartItemId);
                break;
            }
        }

        cart.setCartItem(cartItems);
        cart.setTotalPriceInCart(cartTotalPriceCalculator(cartItems));
        cartRepository.save(cart);


        return SuccessMessages.CART_ITEM_REMOVED;
    }

    public void makeEmptyCartAfterOrder(Long cartId){
        Cart cart = existById(cartId);
        List<CartItem> cartItems = new ArrayList<>();

        cart.setCartItem(cartItems);
        cartRepository.save(cart);
    }
}
