package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.AgencesDto;
import com.example.location_voitures.dtos.VoitureDto;
import com.example.location_voitures.requests.agences.AgencesRequest;
import com.example.location_voitures.responses.AgencesResponse;
import com.example.location_voitures.responses.VoitureResponse;
import com.example.location_voitures.services.AgencesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    AgencesService agencesService;

    @GetMapping()
    public ResponseEntity<List<AgencesResponse>> getAllAgences(){
        List<AgencesDto> agencesDtos = agencesService.getAllAgences();
        List<AgencesResponse> agencesResponses = new ArrayList<>();
        for (AgencesDto agencesDto: agencesDtos){
            AgencesResponse agencesResponse = mapper.map(agencesDto, AgencesResponse.class);
            List<VoitureResponse> voitureResponses = new ArrayList<>();
            for (VoitureDto voitureDto: agencesDto.getVoitures()){
                VoitureResponse voitureResponse = mapper.map(voitureDto, VoitureResponse.class);
                voitureResponses.add(voitureResponse);
            }
            agencesResponse.setVoitures(voitureResponses);
            agencesResponses.add(agencesResponse);
        }
        return new ResponseEntity<List<AgencesResponse>>(agencesResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgencesResponse> getOneAgence(@PathVariable("id") long id) throws Exception {

        AgencesDto agencesDto = agencesService.getOneAgences(id);
        AgencesResponse agencesResponse = mapper.map(agencesDto, AgencesResponse.class);
        List<VoitureResponse> voitureResponses = new ArrayList<>();
        for (VoitureDto voitureDto: agencesDto.getVoitures()){
            VoitureResponse voitureResponse = mapper.map(voitureDto, VoitureResponse.class);
            voitureResponses.add(voitureResponse);
        }
        agencesResponse.setVoitures(voitureResponses);
        return new ResponseEntity<AgencesResponse>(agencesResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<AgencesResponse> createAgence(@RequestBody AgencesRequest agencesRequest){
        AgencesDto dto = mapper.map(agencesRequest, AgencesDto.class);
        AgencesDto agencesDto = agencesService.setOneAgences(dto);
        AgencesResponse agencesResponse = mapper.map(agencesDto, AgencesResponse.class);
        return new ResponseEntity<AgencesResponse>(agencesResponse, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<AgencesResponse> updateAgence(@PathVariable("id") long id, @RequestBody AgencesRequest agencesRequest){
        AgencesDto dto = mapper.map(agencesRequest, AgencesDto.class);
        AgencesDto agencesDto = agencesService.updateOneAgences(id,dto);
        AgencesResponse agencesResponse = mapper.map(agencesDto, AgencesResponse.class);

        return new ResponseEntity<AgencesResponse>(agencesResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgence(@PathVariable("id") long id){
        agencesService.deleteOneAgences(id);
        return new ResponseEntity<String>("Delete user "+id, HttpStatus.GONE);
    }
}
