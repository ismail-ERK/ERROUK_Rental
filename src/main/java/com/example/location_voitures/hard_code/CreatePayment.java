package com.example.location_voitures.hard_code;

import com.example.location_voitures.dtos.LocationDto;
import com.example.location_voitures.dtos.payment.CustomerData;
import com.example.location_voitures.entities.CustomerEntity;
import com.example.location_voitures.entities.LocationEntity;
import com.example.location_voitures.entities.VoitureEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.CustomerRepository;
import com.example.location_voitures.repositories.VoitureRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class CreatePayment {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VoitureRepository voitureRepository;

    public LocationEntity createCustomerWithPayment(LocationDto locationDto, CustomerData data, String stripeKey) throws StripeException {
        LocationEntity locationEntity = mapper.map(locationDto, LocationEntity.class);
        VoitureEntity voiture = voitureRepository.findById(locationDto.getVoitureId());
        if (voiture == null) throw new EntityNotFoundException("Voiture not found !!");
        long difference_In_Time
                = locationDto.getDate_fin().getTime() - locationDto.getDate_debut().getTime();
        long difference_In_Years
                = TimeUnit
                .MILLISECONDS
                .toDays(difference_In_Time)
                % 365;

        //get Ammount
        double ammont = (difference_In_Years + 1) * voiture.getPrixJour();
        System.out.println(ammont);
        // Payment Instructions
        Stripe.apiKey = stripeKey;
        CustomerEntity customer = customerRepository.findByEmail(data.getEmail());
        if (customer == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", data.getName());
            params.put("email", data.getEmail());
            Customer customer1 = Customer.create(params);
            data.setCustomerID(customer1.getId());
            CustomerEntity customer2 = new CustomerEntity();

            customer2.setCustomerID(customer1.getId());
            customer2.setEmail(customer1.getEmail());
            customer2.setName(customer1.getName());
            CustomerEntity customer3 = customerRepository.save(customer2);

            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount((long) ammont * 100)
                    .setCurrency(data.getCurrency())
                    .setCustomer(customer1.getId())
                    .build();
            PaymentIntent intent = PaymentIntent.create(createParams);

            System.out.println(data);
        }else {
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount((long) ammont * 100)
                    .setCurrency(data.getCurrency())
                    .setCustomer(customer.getCustomerID())
                    .build();
            PaymentIntent intent = PaymentIntent.create(createParams);

            System.out.println(data);
        }
        return locationEntity;
    }

}
