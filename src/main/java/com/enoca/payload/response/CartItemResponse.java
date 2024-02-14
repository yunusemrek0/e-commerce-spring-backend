package com.enoca.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CartItemResponse {


    private Long id;

    private Integer quantity;

    private String productName;

    private Double productPrice;

    

}
