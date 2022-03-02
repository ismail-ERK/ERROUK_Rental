package com.example.location_voitures.repositories;

import com.example.location_voitures.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findAll();
    PaymentEntity findById(long id);
}
