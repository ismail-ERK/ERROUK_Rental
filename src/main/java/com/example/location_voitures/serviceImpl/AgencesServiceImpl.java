package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.AgencesDto;
import com.example.location_voitures.dtos.VoitureDto;
import com.example.location_voitures.entities.AgencesEntity;
import com.example.location_voitures.entities.VoitureEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.AgencesRepository;
import com.example.location_voitures.services.AgencesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgencesServiceImpl implements AgencesService {
    ModelMapper mapper = new ModelMapper();

    @Autowired
    AgencesRepository agencesRepository;

    @Override
    public List<AgencesDto> getAllAgences() {
        List<AgencesEntity> agencesEntities = agencesRepository.findAll();
        List<AgencesDto> agencesDtos = new ArrayList<>();
        for (AgencesEntity agenceEntity : agencesEntities){
            AgencesDto agenceDto = mapper.map(agenceEntity, AgencesDto.class);
            List<VoitureDto> voitureDtos = new ArrayList<>();
            for (VoitureEntity voiture : agenceEntity.getVoitures()){
                VoitureDto voitureDto = mapper.map(voiture, VoitureDto.class);
                voitureDtos.add(voitureDto);
            }
            agenceDto.setVoitures(voitureDtos);
            agencesDtos.add(agenceDto);
        }
        return agencesDtos;
    }

    @Override
    public AgencesDto getOneAgences(long id) throws Exception {
        AgencesEntity agencesEntity = agencesRepository.findById(id);
        if(agencesEntity!=null){
            AgencesDto agencesDto = mapper.map(agencesEntity, AgencesDto.class);
            List<VoitureDto> voitureDtos = new ArrayList<>();
            for (VoitureEntity voiture : agencesEntity.getVoitures()){
                VoitureDto voitureDto = mapper.map(voiture, VoitureDto.class);
                voitureDtos.add(voitureDto);
            }
            agencesDto.setVoitures(voitureDtos);
            return agencesDto;
        }else {
            throw new EntityNotFoundException("Agence Not Found");
        }

    }

    @Override
    public AgencesDto setOneAgences(AgencesDto dto) {
        AgencesEntity agencesEntity = mapper.map(dto, AgencesEntity.class);
        AgencesEntity agencesEntity1 = agencesRepository.save(agencesEntity);
        AgencesDto agencesDto = mapper.map(agencesEntity1, AgencesDto.class);
        List<VoitureDto> voitureDtos = new ArrayList<>();
        return agencesDto;
    }

    @Override
    public AgencesDto updateOneAgences(long id, AgencesDto dto) {
        AgencesEntity agencesEntity = agencesRepository.findById(id);
        if(agencesEntity!=null){
            if(dto.getName()!=null){
                agencesEntity.setName(dto.getName());
            }
            if(dto.getVille()!=null){
                agencesEntity.setVille(dto.getVille());
            }
            if(dto.getAdresse()!=null){
                agencesEntity.setAdresse(dto.getAdresse());
            }
            if(dto.getImage360()!=null){
                agencesEntity.setImage360(dto.getImage360());
            }
            AgencesEntity agencesEntity1 = agencesRepository.save(agencesEntity);
            AgencesDto agencesDto = mapper.map(agencesEntity1,AgencesDto.class);
            return agencesDto;
        }else {
            throw new EntityNotFoundException("Agence Not Found");
        }
    }

    @Override
    public void deleteOneAgences(long id) {
        AgencesEntity agencesEntity = agencesRepository.findById(id);
        if(agencesEntity!=null){
            agencesRepository.delete(agencesEntity);
        }else {
            throw new EntityNotFoundException("Agence Not Found");
        }

    }
    public AgencesEntity getOneAgenceEntity(long id) {
        AgencesEntity agencesEntity = agencesRepository.findById(id);
        return agencesEntity;

    }
}
