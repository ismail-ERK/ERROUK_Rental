package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.ImagesDto;
import com.example.location_voitures.entities.ImagesEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.ImagesRepository;
import com.example.location_voitures.repositories.VoitureRepository;
import com.example.location_voitures.services.ImagesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ImagesServiceImpl implements ImagesService {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    ImagesRepository imagesRepository;
    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public List<ImagesDto> getAllImages() {
        List<ImagesEntity> imagesEntities = imagesRepository.findAll();
        List<ImagesDto> imagesDtos = new ArrayList<>();
        for(ImagesEntity imagesEntity : imagesEntities){
            ImagesDto imagesDto = mapper.map(imagesEntity, ImagesDto.class);
            imagesDtos.add(imagesDto);
        }
        return imagesDtos;
    }

    @Override
    public List<ImagesDto> getAllImagesOfVoiture(long id) {
        List<ImagesEntity> imagesEntities = imagesRepository.findByVoiture_Id(id);
        List<ImagesDto> imagesDtos = new ArrayList<>();
        for(ImagesEntity imagesEntity : imagesEntities){
            ImagesDto imagesDto = mapper.map(imagesEntity, ImagesDto.class);
            imagesDtos.add(imagesDto);
        }
        return imagesDtos;
    }

    @Override
    public ImagesDto getOneImage(long id) {
        ImagesEntity imagesEntity = imagesRepository.findById(id);
        if (imagesEntity == null ){
            throw new EntityNotFoundException("Image nt found !!!");
        }
        ImagesDto imagesDto = mapper.map(imagesEntity, ImagesDto.class);
        return imagesDto;
    }

    @Override
    public ImagesDto createOneImage(long id, ImagesDto imagesDto) {
        ImagesEntity imagesEntity = mapper.map(imagesDto, ImagesEntity.class);
        imagesEntity.setId(id);
        ImagesEntity imagesEntity1 = imagesRepository.save(imagesEntity);
        ImagesDto imagesDto1 = mapper.map(imagesEntity1, ImagesDto.class);
        return imagesDto1;
    }

    @Override
    public ImagesDto updateOneImage(long id, ImagesDto imagesDto) {
        try {
            ImagesEntity imagesEntity = imagesRepository.findById(id);
            if(imagesEntity!=null){
                if(imagesDto.getType()!=null){
                    imagesEntity.setType(imagesDto.getType());
                }
                if(imagesDto.getUrl()!=null){
                    imagesEntity.setUrl(imagesDto.getUrl());
                }
            }
            else {
                throw new EntityNotFoundException("Image Not found !!!!");
            }
            return null;
        }catch (Exception e){
            throw new PropertyNotFoundException(e);
        }
    }

    @Override
    public void deleteOneImage(long id) {
        ImagesEntity imagesEntity = imagesRepository.findById(id);
        if(imagesEntity!=null){
            imagesRepository.delete(imagesEntity);
        }else {
            throw new EntityNotFoundException("Image Not found !!!!");
        }
    }
}
