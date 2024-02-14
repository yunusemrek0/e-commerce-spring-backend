package com.enoca.payload.response;

import com.enoca.entity.CartItem;
import com.enoca.entity.Customer;
import com.enoca.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private String customerName;
    private Double totalPrice;
    private List<CartItemResponse> cartItems;

}
