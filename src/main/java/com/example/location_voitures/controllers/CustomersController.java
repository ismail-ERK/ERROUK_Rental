package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.CustomerDto;
import com.example.location_voitures.responses.CustomerResponse;
import com.example.location_voitures.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    CustomerService customerService;

    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (CustomerDto customerDto : customerDtos) {
            CustomerResponse customerResponse = mapper.map(customerDto, CustomerResponse.class);
            customerResponses.add(customerResponse);
        }
        return new ResponseEntity<List<CustomerResponse>>(customerResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getOneCustomers(@PathVariable("id") long id) {
        CustomerDto customerDto = customerService.getOneCustomer(id);
        CustomerResponse customerResponse = mapper.map(customerDto, CustomerResponse.class);

        return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> seleteOneCustomers(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<String>("Customer has deleted !!", HttpStatus.NO_CONTENT);
    }
}
