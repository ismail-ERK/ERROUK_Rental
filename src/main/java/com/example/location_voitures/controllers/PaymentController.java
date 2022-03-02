package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.payment.PaymentDto;
import com.example.location_voitures.responses.PaymentResponse;
import com.example.location_voitures.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    PaymentService paymentService;
    @GetMapping
    ResponseEntity<List<PaymentResponse>> getAllPayments(){
        List<PaymentDto> paymentDtos = paymentService.getAllPayments();
        List<PaymentResponse> paymentResponses = new ArrayList<>();
        for (PaymentDto paymentDto : paymentDtos) {
            PaymentResponse paymentResponse = mapper.map(paymentDto, PaymentResponse.class);
            paymentResponses.add(paymentResponse);
        }
        return new ResponseEntity<List<PaymentResponse>>(paymentResponses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> getOnePayment(@PathVariable("id") long id){
        PaymentDto paymentDto = paymentService.getOnePayment(id);
        PaymentResponse paymentResponse = mapper.map(paymentDto, PaymentResponse.class);
        return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteOnePayment(@PathVariable("id") long id){
        paymentService.deleteOnePayment(id);
        return new ResponseEntity<String>("Payment has deleted !!:", HttpStatus.OK);
    }


}
