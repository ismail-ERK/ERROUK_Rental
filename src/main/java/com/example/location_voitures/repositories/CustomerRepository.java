package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    CustomerEntity findByEmail(String email);
    CustomerEntity findById(long id);
    CustomerEntity findByCustomerID(String id);
    List<CustomerEntity> findAll();
}
