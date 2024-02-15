package com.enoca.payload.response;

import com.enoca.payload.response.abstractresponse.BaseAbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductResponse extends BaseAbstractResponse {

    private String name;

    private Double price;

    private Integer stockQuantity;


}
