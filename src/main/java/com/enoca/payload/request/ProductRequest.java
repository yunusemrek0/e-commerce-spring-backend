package com.enoca.payload.request;


import lombok.*;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotNull(message = "Product name can not be null")
    private String name;

    @DecimalMin(value = "0.0", message = "Price can not be smaller than zero")
    @NotNull(message = "Product price can not be null")
    private Double price;

    @NotNull(message = "Product quantity can not be null")
    @DecimalMin(value = "0.0", message = "Product quantity can not be smaller than zero")
    private Integer stockQuantity;


}
