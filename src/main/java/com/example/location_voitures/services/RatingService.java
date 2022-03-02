package com.example.location_voitures.services;

import com.example.location_voitures.dtos.RatingDto;

import java.util.List;

public interface RatingService {
    public List<RatingDto> getAllRating();
    public RatingDto getOneRating(long userId, long voitureId);
    public RatingDto setOneRating(RatingDto ratingDto);
    public RatingDto updateOneRating(long userId, long voitureId, RatingDto ratingDto);
    public void deleteOneRating(long userId, long voitureId);
}
