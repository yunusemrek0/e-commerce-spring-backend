package com.enoca.payload.response;

import com.enoca.payload.response.abstractresponse.BaseAbstractResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemResponse extends BaseAbstractResponse {


    private Long id;

    private Integer quantity;

    private String productName;

    private Double productPrice;

    

}
