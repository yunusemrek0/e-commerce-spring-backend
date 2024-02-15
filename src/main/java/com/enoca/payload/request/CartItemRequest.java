package com.enoca.payload.request;

import com.enoca.payload.request.abstractrequest.BaseAbstractRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CartItemRequest extends BaseAbstractRequest {

    @NotNull(message = "Product quantity can not be null")
    @Min(value = 1, message = "Quantity can not be smaller than 1")
    private Integer quantity;

    @NotNull(message = "Product ID can not be null")
    private Long productId;

}
