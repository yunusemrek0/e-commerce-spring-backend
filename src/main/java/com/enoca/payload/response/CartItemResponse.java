package com.enoca.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CartItemResponse {


    private Long id;

    private Integer quantity;

    private String productName;

    private Double productPrice;

    

}
