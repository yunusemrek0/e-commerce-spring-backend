package com.enoca.payload.request;


import com.enoca.payload.request.abstractrequest.BaseAbstractRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest extends BaseAbstractRequest {

    @NotNull
    private Long customerId;



}
