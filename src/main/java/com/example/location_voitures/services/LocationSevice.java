package com.example.location_voitures.services;

import com.example.location_voitures.dtos.LocationDto;
import com.example.location_voitures.dtos.payment.CustomerData;
import com.example.location_voitures.entities.CleCompose.LigneLocationKey;
import com.stripe.exception.StripeException;

import java.util.List;

public interface LocationSevice {
    public List<LocationDto> getAllLocations();
    public LocationDto getOneLocations(long userId, long voitureId);
    public LocationDto setOneLocations(LocationDto locationDto, CustomerData customerData) throws StripeException;
    public LocationDto updateOneLocations(long userId, long voitureId,LocationDto locationDto);
    public void deleteOneLocations(long userId, long voitureId);
}
