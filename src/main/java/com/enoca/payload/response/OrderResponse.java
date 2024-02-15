package com.enoca.payload.response;


import com.enoca.payload.response.abstractresponse.BaseAbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.util.List;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse extends BaseAbstractResponse {


    private Long id;

    private Double totalPrice;

    private String customerName;

    private List<CartItemResponse> cartItems;

    private String code;

}
