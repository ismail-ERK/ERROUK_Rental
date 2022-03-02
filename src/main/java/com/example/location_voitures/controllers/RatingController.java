package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.RatingDto;
import com.example.location_voitures.requests.ratings.RatingRequest;
import com.example.location_voitures.requests.ratings.RatingUpdateRequest;
import com.example.location_voitures.responses.RatingResponse;
import com.example.location_voitures.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    RatingService ratingService;

    @GetMapping
    ResponseEntity<List<RatingResponse>> getAllRatings() {
        List<RatingDto> ratingDtos = ratingService.getAllRating();
        List<RatingResponse> ratingResponses = new ArrayList<>();
        for (RatingDto ratingDto : ratingDtos) {
            RatingResponse ratingResponse = mapper.map(ratingDto, RatingResponse.class);
            ratingResponses.add(ratingResponse);
        }
        return new ResponseEntity<List<RatingResponse>>(ratingResponses, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{voitureId}")
    ResponseEntity<RatingResponse> getOneRating(@PathVariable(name = "userId") long userId, @PathVariable(name = "voitureId") long voitureId) {
        RatingDto ratingDto = ratingService.getOneRating(userId, voitureId);
        RatingResponse ratingResponse = mapper.map(ratingDto, RatingResponse.class);
        return new ResponseEntity<RatingResponse>(ratingResponse, HttpStatus.OK);
    }


    @PostMapping()
    ResponseEntity<RatingResponse> createOneRating(@RequestBody RatingRequest ratingRequest) {
        RatingDto ratingDto = mapper.map(ratingRequest, RatingDto.class);
        RatingDto ratingDto1 = ratingService.setOneRating(ratingDto);
        RatingResponse ratingResponse = mapper.map(ratingDto1, RatingResponse.class);
        return new ResponseEntity<RatingResponse>(ratingResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{voitureId}")
    ResponseEntity<RatingResponse> updateOneRating(@PathVariable(name = "userId") long userId,
                                                   @PathVariable(name = "voitureId") long voitureId,
                                                   @RequestBody RatingUpdateRequest ratingUpdateRequest) {
        RatingDto ratingDto = mapper.map(ratingUpdateRequest, RatingDto.class);
        RatingDto ratingDto1 = ratingService.updateOneRating(userId, voitureId, ratingDto);
        RatingResponse ratingResponse = mapper.map(ratingDto1, RatingResponse.class);
        return new ResponseEntity<RatingResponse>(ratingResponse, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{userId}/{voitureId}")
    ResponseEntity<String> deleteOneRating(@PathVariable(name = "userId") long userId,
                                                   @PathVariable(name = "voitureId") long voitureId) {
        ratingService.deleteOneRating(userId, voitureId);
        return new ResponseEntity<String>("Rating has deleted", HttpStatus.NO_CONTENT);
    }
}
