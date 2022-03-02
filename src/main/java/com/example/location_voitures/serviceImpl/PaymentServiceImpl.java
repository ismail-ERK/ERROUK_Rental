package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.payment.PaymentDto;
import com.example.location_voitures.entities.PaymentEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.PaymentRepository;
import com.example.location_voitures.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<PaymentDto> getAllPayments() {
        List<PaymentEntity> entities = paymentRepository.findAll();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity paymentEntity : entities) {
            PaymentDto paymentDto = mapper.map(paymentEntity, PaymentDto.class);
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }

    @Override
    public PaymentDto getOnePayment(long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id);
        if (paymentEntity == null) throw new EntityNotFoundException("Payment not found !!");
        PaymentDto paymentDto = mapper.map(paymentEntity, PaymentDto.class);
        return paymentDto;
    }

    @Override
    public void deleteOnePayment(long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id);
        if (paymentEntity == null) throw new EntityNotFoundException("Payment not found !!");
        paymentRepository.delete(paymentEntity);
    }
}
