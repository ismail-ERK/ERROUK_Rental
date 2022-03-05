package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.CustomerDto;
import com.example.location_voitures.entities.CustomerEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.CustomerRepository;
import com.example.location_voitures.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> entities = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity customer: entities){
            CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }

    @Override
    public CustomerDto getOneCustomer(long id) {
        CustomerEntity customer = customerRepository.findById(id);
        if(customer == null) throw new EntityNotFoundException("Customer not found");
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(long id) {
        CustomerEntity customer = customerRepository.findById(id);
        if(customer == null) throw new EntityNotFoundException("Customer not found");
        customerRepository.delete(customer);
    }
}
