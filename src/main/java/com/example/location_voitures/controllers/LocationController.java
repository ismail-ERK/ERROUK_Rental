package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.LocationDto;
import com.example.location_voitures.dtos.payment.CustomerData;
import com.example.location_voitures.dtos.payment.PaymentWithLocationInfos;
import com.example.location_voitures.requests.locations.LocationRequest;
import com.example.location_voitures.responses.LocationResponse;
import com.example.location_voitures.services.LocationSevice;
import com.stripe.exception.StripeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    LocationSevice locationSevice;

    @GetMapping()
    ResponseEntity<List<LocationResponse>> getAllLocations(){
        List<LocationDto> locationDtos = locationSevice.getAllLocations();
        List<LocationResponse> locationResponses = new ArrayList<>();
        for (LocationDto locationDto : locationDtos){
            LocationResponse locationResponse = mapper.map(locationDto, LocationResponse.class);
            locationResponses.add(locationResponse);
        }
        return new ResponseEntity<List<LocationResponse>>(locationResponses, HttpStatus.OK);
    }
    @GetMapping("/{userId}/{voitureId}")
    ResponseEntity<LocationResponse> getOneLocations(@PathVariable(name = "userId") long userId,
                                                     @PathVariable(name = "voitureId") long voitureId){
        LocationDto locationDto = locationSevice.getOneLocations(userId, voitureId);
        LocationResponse locationResponse = mapper.map(locationDto, LocationResponse.class);
        return new ResponseEntity<LocationResponse>(locationResponse, HttpStatus.OK);

    }
    @PostMapping()
    ResponseEntity<LocationResponse> setOneLocations(@RequestBody PaymentWithLocationInfos locationRequestWithPayment) throws StripeException {
        LocationDto locationDto = mapper.map(locationRequestWithPayment.getLocationRequest(), LocationDto.class);
        LocationDto locationDto1 = locationSevice.setOneLocations(locationDto,locationRequestWithPayment.getCustomerData());
        LocationResponse locationResponse = mapper.map(locationDto1, LocationResponse.class);
        return new ResponseEntity<LocationResponse>(locationResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}/{voitureId}")
    ResponseEntity<LocationResponse> updateOneLocations(@PathVariable(name = "userId") long userId, @PathVariable(name = "voitureId") long voitureId,@RequestBody LocationRequest locationRequest){
        LocationDto locationDto = mapper.map(locationRequest, LocationDto.class);
        LocationDto locationDto1 = locationSevice.updateOneLocations(userId,voitureId,locationDto);
        LocationResponse locationResponse = mapper.map(locationDto1, LocationResponse.class);
        return new ResponseEntity<LocationResponse>(locationResponse, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{userId}/{voitureId}")
    ResponseEntity<String> deleteOneLocations(@PathVariable(name = "userId") long userId, @PathVariable(name = "voitureId") long voitureId){
       locationSevice.deleteOneLocations(userId, voitureId);
       return new ResponseEntity<String>("Location has deleted !!",HttpStatus.NO_CONTENT);
    }
}
