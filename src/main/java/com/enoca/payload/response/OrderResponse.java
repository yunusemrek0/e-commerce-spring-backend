package com.enoca.payload.response;


import com.enoca.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {


    private Long id;

    private Double totalPrice;

    private String customerName;

    private List<CartItemResponse> cartItems;

    private String code;

}
