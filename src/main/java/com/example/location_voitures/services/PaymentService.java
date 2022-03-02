package com.example.location_voitures.services;

import com.example.location_voitures.dtos.payment.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAllPayments();
    PaymentDto getOnePayment(long id);
    void deleteOnePayment(long id);
}
