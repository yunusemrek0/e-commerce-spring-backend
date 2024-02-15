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
public class OrderRequest extends BaseAbstractRequest {

    @NotNull(message = "Please enter customerId")
    private Long customerId;

}
