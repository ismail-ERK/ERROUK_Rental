package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.RatingDto;
import com.example.location_voitures.entities.CleCompose.RatingId;
import com.example.location_voitures.entities.RatingEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.RatingRepository;
import com.example.location_voitures.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    ModelMapper mapper = new ModelMapper();

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<RatingDto> getAllRating() {
        List<RatingEntity> entities = ratingRepository.findAll();
        List<RatingDto> ratingDtos = new ArrayList<>();
        for (RatingEntity rating : entities) {
            RatingDto ratingDto = mapper.map(rating, RatingDto.class);
            ratingDtos.add(ratingDto);
        }
        return ratingDtos;
    }

    @Override
    public RatingDto getOneRating(long userId, long voitureId) {
        RatingId ratingId = new RatingId(userId, voitureId);
        RatingEntity entities = ratingRepository.findByRatingId(ratingId);
        if(entities == null ){
            throw new EntityNotFoundException("Rating not found !!!");
        }
        RatingDto ratingDto = mapper.map(entities, RatingDto.class);

        return ratingDto;
    }

    @Override
    public RatingDto setOneRating(RatingDto ratingDto) {
        RatingEntity rating = mapper.map(ratingDto, RatingEntity.class);
        RatingEntity rating1 = ratingRepository.save(rating);
        RatingDto ratingDto1 = mapper.map(rating1, RatingDto.class);
        return ratingDto1;
    }

    @Override
    public RatingDto updateOneRating(long userId, long voitureId, RatingDto ratingDto) {
        RatingId ratingId = new RatingId(userId, voitureId);

        RatingEntity rating = ratingRepository.findByRatingId(ratingId);
        if(rating != null){
            rating.setValue(ratingDto.getValue());
            RatingEntity rating1 = ratingRepository.save(rating);
            RatingDto ratingDto1 = mapper.map(rating1, RatingDto.class);
            return ratingDto1;
        }else {
            throw new EntityNotFoundException("Rating not found !!");
        }
    }

    @Override
    public void deleteOneRating(long userId, long voitureId) {
        RatingId ratingId = new RatingId(userId, voitureId);
        RatingEntity rating = ratingRepository.findByRatingId(ratingId);
        if(rating != null){
            ratingRepository.delete(rating);
        }else {
            throw new EntityNotFoundException("Rating not found !!");
        }
    }
}
