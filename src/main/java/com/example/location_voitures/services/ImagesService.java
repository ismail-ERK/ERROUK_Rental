package com.example.location_voitures.services;

import com.example.location_voitures.dtos.ImagesDto;

import java.util.List;

public interface ImagesService {
    public List<ImagesDto> getAllImages();
    public List<ImagesDto> getAllImagesOfVoiture(long id);
    public ImagesDto getOneImage(long id);
    public ImagesDto createOneImage(long id,ImagesDto imagesDto);
    public ImagesDto updateOneImage(long id,ImagesDto imagesDto);
    public void deleteOneImage(long id);

}
