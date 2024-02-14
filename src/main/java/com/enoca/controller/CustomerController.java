package com.enoca.controller;

import com.enoca.payload.request.CustomerRequest;
import com.enoca.payload.response.CustomerResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/addCustomer") //http://localhost:8080/customer/addCustomer
    public ResponseEntity<ResponseMessage<CustomerResponse>> addCustomer(
            @RequestBody @Valid CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.addCustomer(customerRequest));
    }

}
