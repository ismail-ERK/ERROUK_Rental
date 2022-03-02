package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.CarsCriteria;
import com.example.location_voitures.dtos.VoitureDto;
import com.example.location_voitures.entities.AgencesEntity;
import com.example.location_voitures.entities.VoitureEntity;
import com.example.location_voitures.repositories.AgencesRepository;
import com.example.location_voitures.repositories.VoitureRepository;
import com.example.location_voitures.services.VoitureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoitureServiceImpl implements VoitureService {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    VoitureRepository voitureRepository;
    @Autowired
    AgencesRepository agencesRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    public List<VoitureDto> getAllvoitures(CarsCriteria carsCriteria) {
        String query = "select item from VoitureEntity item WHERE 1=1";
        if(carsCriteria.getAgence()!=null){
            query+= " AND item.agence.id = "+carsCriteria.getAgence();
        }
        if(carsCriteria.getMarque()!=null){
            query+= " AND item.marque LIKE '%"+carsCriteria.getMarque()+"%'";
        }


        if(carsCriteria.getModele()!=null){
            query+= " AND item.modele LIKE '%"+carsCriteria.getModele()+"%'";
        }
        if(carsCriteria.getTransmission()!=null){
            query+= " AND item.transmission LIKE '%"+carsCriteria.getTransmission()+"%'";
        }
        if(carsCriteria.getNbrPlaces()!=null){
            query+= " AND item.nbrPlaces = "+carsCriteria.getNbrPlaces();
        }
        if(carsCriteria.getNbrPortes()!=null){
            query+= " AND item.nbrPortes = "+carsCriteria.getNbrPortes();
        }

        if(carsCriteria.getPrixMin()!=null){
            query+= " AND item.prixJour >= "+carsCriteria.getPrixMin();
        }
        if(carsCriteria.getPrixMax()!=null){
            query+= " AND item.prixJour <= "+carsCriteria.getPrixMax();
        }
    List<VoitureEntity> voitureEntities = entityManager.createQuery(query).getResultList();
        List<VoitureDto> voitureDtos = new ArrayList<>();
        for (VoitureEntity voiture : voitureEntities){
            VoitureDto voitureDto = mapper.map(voiture, VoitureDto.class);
            voitureDtos.add(voitureDto);
        }
        return voitureDtos;
    }

    @Override
    public List<VoitureDto> getAllVoittureOfAgence(long id) {
        List<VoitureEntity> voitureEntities = voitureRepository.findByAgence_Id(id);
        List<VoitureDto> voitureDtos = new ArrayList<>();
        for (VoitureEntity voiture : voitureEntities){
            VoitureDto voitureDto = mapper.map(voiture, VoitureDto.class);
            voitureDtos.add(voitureDto);
        }
        return voitureDtos;
    }

    @Override
    public VoitureDto createOneVoiture(long id, VoitureDto voitureDto) {
        VoitureEntity voiture = mapper.map(voitureDto, VoitureEntity.class);
        AgencesEntity agencesEntity = agencesRepository.findById(id);
        voiture.setAgence(agencesEntity);
        VoitureEntity voiture1 = voitureRepository.save(voiture);
        VoitureDto voitureDto1 = mapper.map(voiture1, VoitureDto.class);
        return voitureDto1;
    }

    @Override
    public VoitureDto updateOneVoiture(long id, VoitureDto voitureDto) {
        VoitureEntity voiture = voitureRepository.findById(id);
        VoitureEntity voiture1 = mapper.map(voitureDto, VoitureEntity.class);
        VoitureEntity voiture2 = new VoitureEntity();
        if(voiture != null){
            voiture1.setId(voiture.getId());
            voiture1.setAgence(voiture.getAgence());
            voiture2 = voitureRepository.save(voiture1);
            VoitureDto voitureDto1 = mapper.map(voiture2, VoitureDto.class);
        return voitureDto1;
        }else {
            throw new PropertyNotFoundException("Not Found");
        }
    }

    @Override
    public void deleteOneVoiture(long id) {
        VoitureEntity voiture = voitureRepository.findById(id);

        if(voiture != null){
            voitureRepository.delete(voiture);
        }else {
            throw new PropertyNotFoundException("Not Found");
        }
    }
}
