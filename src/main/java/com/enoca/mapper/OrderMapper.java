package com.enoca.mapper;

import com.enoca.entity.CartItem;
import com.enoca.entity.Customer;
import com.enoca.entity.Order;
import com.enoca.payload.request.OrderRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.OrderResponse;
import com.enoca.service.CartItemService;
import com.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CartService cartService;
    private final CartItemMapper cartItemMapper;


    public Order mapOrderRequestToOrder(OrderRequest orderRequest,
                                        Customer customer,
                                        List<CartItem> cartItems,
                                        String code){

        return Order.builder()
                .code(code)
                .cartItem(cartItems)
                .customer(customer)
                .totalPrice(cartService.cartTotalPriceCalculator(cartItems))
                .creationDate(orderRequest.getCreationDate())
                .build();
    }

    public OrderResponse mapOrderToOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .code(order.getCode())
                .customerName(order.getCustomer().getName())
                .cartItems(order.getCartItem()
                        .stream()
                        .map(cartItemMapper::mapCartItemToCartItemResponse)
                        .collect(Collectors.toList()))
                .creationDate(order.getCreationDate())
                .build();
    }



}
