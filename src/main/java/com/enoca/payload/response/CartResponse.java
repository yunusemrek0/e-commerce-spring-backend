package com.enoca.payload.response;

import com.enoca.payload.response.abstractresponse.BaseAbstractResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse extends BaseAbstractResponse {

    private String customerName;
    private Double totalPrice;
    private List<CartItemResponse> cartItems;

}
