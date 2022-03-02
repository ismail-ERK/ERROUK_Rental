package com.example.location_voitures.services;

import com.example.location_voitures.dtos.AgencesDto;

import java.util.List;

public interface AgencesService {
    public List<AgencesDto> getAllAgences();
    public AgencesDto getOneAgences(long id) throws Exception;
    public AgencesDto setOneAgences(AgencesDto dto);
    public AgencesDto updateOneAgences(long id, AgencesDto dto);
    public void deleteOneAgences(long id);

}
