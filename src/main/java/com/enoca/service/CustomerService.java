package com.enoca.service;

import com.enoca.entity.Customer;
import com.enoca.exception.ConflictException;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.mapper.CustomerMapper;
import com.enoca.message.ErrorMessages;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.CustomerRequest;
import com.enoca.payload.response.CustomerResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public ResponseMessage<CustomerResponse> addCustomer(CustomerRequest customerRequest) {
        String username = customerRequest.getUsername(); //customerRequest'ten username'i aldık
        boolean isExistByUsername = customerRepository.existsByUsername(username); //username unique mi diye kontrol ettik
        if (isExistByUsername){
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_USERNAME, username));
        }
        String email = customerRequest.getEmail();
        boolean isExistByEmail = customerRepository.existsByEmail(email); //username unique mi diye kontrol ettik
        if (isExistByEmail){
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, email));
        }
        Customer customer = customerMapper.mapCustomerRequestToCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);

        return ResponseMessage.<CustomerResponse>builder()
                .message(SuccessMessages.CUSTOMER_CREATE)
                .returnBody(customerMapper.mapCustomerToCustomerResponse(savedCustomer))
                .build();
    }


    //add helper
    public Customer existsById(Long id){
        return customerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CUSTOMER_ID,id))
        );
    }
}
