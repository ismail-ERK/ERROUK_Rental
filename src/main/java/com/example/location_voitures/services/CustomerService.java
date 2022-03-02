package com.example.location_voitures.services;

import com.example.location_voitures.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getOneCustomer(long id);
    void deleteCustomer(long id);
}
