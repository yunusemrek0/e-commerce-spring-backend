package com.enoca.controller;


import com.enoca.payload.request.OrderRequest;
import com.enoca.payload.response.OrderResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder") //http://localhost:8080/order/placeOrder
    public ResponseEntity<String> placeOrder(
            @RequestBody @Valid OrderRequest orderRequest){

        return ResponseEntity.ok(orderService.placeOrder(orderRequest));

    }

    @GetMapping("/getByCode")
    public ResponseMessage<OrderResponse> getByCode(@RequestParam String code){

        return orderService.getByCode(code);

    }

    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<List<OrderResponse>> getByCustomerId(@PathVariable Long customerId){

        return ResponseEntity.ok(orderService.getByCustomerId(customerId));

    }



}
