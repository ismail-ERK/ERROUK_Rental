package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.LocationDto;
import com.example.location_voitures.dtos.payment.CustomerData;
import com.example.location_voitures.entities.CleCompose.LigneLocationKey;
import com.example.location_voitures.entities.LocationEntity;
import com.example.location_voitures.repositories.LocationRepository;
import com.example.location_voitures.services.LocationSevice;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationServiceImpl implements LocationSevice {
    ModelMapper mapper = new ModelMapper();
    @Value("${stripe.apikey}")
    String stripeKey;
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<LocationDto> getAllLocations() {
        List<LocationEntity> entities = locationRepository.findAll();
        List<LocationDto> locationDtos = new ArrayList<>();
        for (LocationEntity locationEntity : entities){
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

        // Payment Instructions
        Stripe.apiKey = stripeKey;
        Map<String, Object> params= new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());
        Customer customer = Customer.create(params);
        data.setCustomerID(customer.getId());

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
        if(locationEntity!=null){
            if (locationDto.getDate_debut()!=null){
                locationEntity.setDate_debut(locationDto.getDate_debut());
            }
            if (locationDto.getDate_fin()!=null){
                locationEntity.setDate_fin(locationDto.getDate_fin());
            }
            LocationEntity locationEntity1 = locationRepository.save(locationEntity);
            LocationDto locationDto1 = mapper.map(locationEntity1, LocationDto.class);
            return locationDto1;
        }else {
            throw new PropertyNotFoundException("Location not found !!!!");
        }
    }

    @Override
    public void deleteOneLocations(long userId, long voitureId) {
    LigneLocationKey ligneLocationKey = new LigneLocationKey(userId, voitureId);
    LocationEntity locationEntity = locationRepository.findByLocationId(ligneLocationKey);
    if(locationEntity!=null){
        locationRepository.delete(locationEntity);
    }else {
        throw new PropertyNotFoundException("Location not found !!!!");
    }
    }
}
