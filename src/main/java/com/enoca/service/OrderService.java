package com.enoca.service;

import com.enoca.entity.CartItem;
import com.enoca.entity.Customer;
import com.enoca.entity.Order;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.mapper.CartItemMapper;
import com.enoca.mapper.OrderMapper;
import com.enoca.message.ErrorMessages;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.OrderRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.OrderResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderMapper orderMapper;
    private final CartItemService cartItemService;
    private final ProductService productService;


    private static final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMddHHmmssmsms");

    public String placeOrder(OrderRequest orderRequest) {

        Customer customer = customerService.existsById(orderRequest.getCustomerId());

        List<CartItem> cartItems = cartItemService.getByCartId(customer.getCart().getId());
        cartItems.forEach(
                cartItem -> cartItem.setProductPrice(cartItem.getProduct().getPrice()
                ));

        productService.isAvaliableForSale(cartItems);

        String code =uniqueCodeMaker(customer.getId());

        Order order = orderMapper.mapOrderRequestToOrder(orderRequest,customer,cartItems,code);

        Order savedOrder = orderRepository.save(order);

        cartItemService.updateCartItemOrder(savedOrder,cartItems);

        productService.decreasingProductQuantity(cartItems);

        cartItemService.makeEmptyToCart(cartItems);


        return SuccessMessages.ORDER_CREATE;


    }


    private String uniqueCodeMaker(Long customerId){
        LocalDateTime localDateTime = LocalDateTime.now();
        String companyName= "ENOCA";
        String stringLocalDateTime = localDateTime.format(formatter);
        String stringCustomerId = String.valueOf(customerId);

        return companyName +stringLocalDateTime +stringCustomerId;

    }

    public ResponseMessage<OrderResponse> getByCode(String code) {
        Order order = orderRepository.getByCode(code).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ORDER_CODE,code))
        );

        return ResponseMessage.<OrderResponse> builder()
                .message(SuccessMessages.ORDER_FOUND)
                .returnBody(orderMapper.mapOrderToOrderResponse(order))
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public List<OrderResponse> getByCustomerId(Long customerId) {

        customerService.existsById(customerId);

        List<Order> orders = orderRepository.getByCustomerId(customerId);

        return orders
                .stream()
                .map(orderMapper::mapOrderToOrderResponse)
                .collect(Collectors.toList());


    }



}
