package com.example.location_voitures.serviceImpl;

import static java.time.temporal.ChronoUnit.DAYS;

import com.example.location_voitures.dtos.LocationDto;
import com.example.location_voitures.dtos.payment.CustomerData;
import com.example.location_voitures.entities.CleCompose.LigneLocationKey;
import com.example.location_voitures.entities.LocationEntity;
import com.example.location_voitures.entities.VoitureEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.LocationRepository;
import com.example.location_voitures.repositories.VoitureRepository;
import com.example.location_voitures.services.LocationSevice;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.PaymentIntentCreateParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LocationServiceImpl implements LocationSevice {
    ModelMapper mapper = new ModelMapper();
    @Value("${stripe.apikey}")
    String stripeKey;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public List<LocationDto> getAllLocations() {
        List<LocationEntity> entities = locationRepository.findAll();
        List<LocationDto> locationDtos = new ArrayList<>();
        for (LocationEntity locationEntity : entities) {
            LocationDto locationDto1 = mapper.map(locationEntity, LocationDto.class);
            locationDtos.add(locationDto1);
        }
        return locationDtos;
    }

    @Override
    public LocationDto getOneLocations(long userId, long voitureId) {
        LigneLocationKey ligneLocationKey = new LigneLocationKey(userId, voitureId);
        LocationEntity locationEntity = locationRepository.findByLocationId(ligneLocationKey);
        LocationDto locationDto = mapper.map(locationEntity, LocationDto.class);
        return locationDto;
    }

    @Override
    public LocationDto setOneLocations(LocationDto locationDto, CustomerData data) throws StripeException {
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
        Map<String, Object> params = new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());
        Customer customer = Customer.create(params);
        data.setCustomerID(customer.getId());
//        Map<String, Object> charges= new HashMap<>();
//        params.put("currency", data.getCurrency());
//        params.put("amount", ammont);
//        params.put("source", "tok_mastercard");
//        params.put("card", new Card());
//        params.put("customer", customer.getId());
//        Charge charge = Charge.create(charges);
//        data.setChargeId(charge.getId());


        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount((long) ammont*100)
                .setCurrency(data.getCurrency())
                .setCustomer(customer.getId())
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);

        System.out.println(data);


        //if payment has done succesfully


        LocationEntity locationEntity1 = locationRepository.save(locationEntity);
        LocationDto locationDto1 = mapper.map(locationEntity1, LocationDto.class);
        return locationDto1;
    }

    @Override
    public LocationDto updateOneLocations(long userId, long voitureId, LocationDto locationDto) {
        LigneLocationKey ligneLocationKey = new LigneLocationKey(userId, voitureId);
        LocationEntity locationEntity = locationRepository.findByLocationId(ligneLocationKey);
        if (locationEntity != null) {
            if (locationDto.getDate_debut() != null) {
                locationEntity.setDate_debut(locationDto.getDate_debut());
            }
            if (locationDto.getDate_fin() != null) {
                locationEntity.setDate_fin(locationDto.getDate_fin());
            }
            LocationEntity locationEntity1 = locationRepository.save(locationEntity);
            LocationDto locationDto1 = mapper.map(locationEntity1, LocationDto.class);
            return locationDto1;
        } else {
            throw new PropertyNotFoundException("Location not found !!!!");
        }
    }

    @Override
    public void deleteOneLocations(long userId, long voitureId) {
        LigneLocationKey ligneLocationKey = new LigneLocationKey(userId, voitureId);
        LocationEntity locationEntity = locationRepository.findByLocationId(ligneLocationKey);
        if (locationEntity != null) {
            locationRepository.delete(locationEntity);
        } else {
            throw new PropertyNotFoundException("Location not found !!!!");
        }
    }
}
