package com.enoca.payload.request;

import com.enoca.entity.Customer;
import com.enoca.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    @NotNull
    private Long customerId;



}
