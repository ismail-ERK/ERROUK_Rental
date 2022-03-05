package com.example.location_voitures.services;

import com.example.location_voitures.dtos.CarsCriteria;
import com.example.location_voitures.dtos.VoitureDto;

import java.util.List;

public interface VoitureService {
    public List<String> getAllColors();
    public List<String> getAllMarques();
    public List<String> getAllModels();
    public List<String> getAllTransmissions();
    public List<VoitureDto> getAllvoitures(CarsCriteria carsCriteria);
    public List<VoitureDto> getAllVoittureOfAgence(long id);
    public VoitureDto getOneVoitture(long id);
    public VoitureDto createOneVoiture(long id, VoitureDto voitureDto);
    public VoitureDto updateOneVoiture(long id, VoitureDto voitureDto);
    public void deleteOneVoiture(long id);
}
