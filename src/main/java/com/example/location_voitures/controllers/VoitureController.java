package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.CarsCriteria;
import com.example.location_voitures.dtos.ImagesDto;
import com.example.location_voitures.dtos.VoitureDto;
import com.example.location_voitures.requests.voitures.VoitureRequest;
import com.example.location_voitures.responses.ImagesResponse;
import com.example.location_voitures.responses.VoitureResponse;
import com.example.location_voitures.services.VoitureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    VoitureService voitureService;
    @GetMapping()
    public ResponseEntity<List<VoitureResponse>> getAllvoitures(CarsCriteria carsCriteria){
        List<VoitureDto> voitureDtos = voitureService.getAllvoitures(carsCriteria);
        return getListResponseEntity(voitureDtos);
    }
    @GetMapping("/colors")
    public ResponseEntity<List<String>> getAllcolores(){
        List<String> colors = voitureService.getAllColors();
        return new ResponseEntity<List<String>>(colors, HttpStatus.OK);
    }
    @GetMapping("/marques")
    public ResponseEntity<List<String>> getAllMarques(){
        List<String> marques = voitureService.getAllMarques();
        return new ResponseEntity<List<String>>(marques, HttpStatus.OK);
    }
    @GetMapping("/modeles")
    public ResponseEntity<List<String>> getAllModels(){
        List<String> models = voitureService.getAllModels();
        return new ResponseEntity<List<String>>(models, HttpStatus.OK);
    }
    @GetMapping("/transmissions")
    public ResponseEntity<List<String>> getAllTransmissions(){
        List<String> transmissions = voitureService.getAllTransmissions();
        return new ResponseEntity<List<String>>(transmissions, HttpStatus.OK);
    }
    @GetMapping("/agences/{id}")
    public ResponseEntity<List<VoitureResponse>> getAllvoituresByAgence(@PathVariable("id") long id){
        List<VoitureDto> voitureDtos = voitureService.getAllVoittureOfAgence(id);
        return getListResponseEntity(voitureDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VoitureResponse> getOneVoiture(@PathVariable("id") long id){
        VoitureDto voitureDto = voitureService.getOneVoitture(id);
        List<ImagesResponse> imagesResponses = new ArrayList<>();
        for (ImagesDto imagesDto : voitureDto.getImages()){
            ImagesResponse imagesResponse = mapper.map(imagesDto, ImagesResponse.class);
            imagesResponses.add(imagesResponse);
        }
        VoitureResponse voitureResponse = mapper.map(voitureDto, VoitureResponse.class);
        voitureResponse.setImages(imagesResponses);
        return new ResponseEntity<VoitureResponse>(voitureResponse, HttpStatus.OK);
    }
    @PostMapping("/agence/{id}")
    public ResponseEntity<VoitureResponse> createVoiture(@PathVariable("id") long id, @RequestBody VoitureRequest voitureRequest){
        VoitureDto voitureDto = mapper.map(voitureRequest, VoitureDto.class);
        VoitureDto voitureDto1 = voitureService.createOneVoiture(id, voitureDto);
        VoitureResponse voitureResponse = mapper.map(voitureDto1, VoitureResponse.class);
        return new ResponseEntity<VoitureResponse>(voitureResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoitureResponse> updateVoiture(@PathVariable("id") long id, @RequestBody VoitureRequest voitureRequest){
        VoitureDto voitureDto = mapper.map(voitureRequest, VoitureDto.class);
        VoitureDto voitureDto1 = voitureService.updateOneVoiture(id, voitureDto);
        VoitureResponse voitureResponse = mapper.map(voitureDto1, VoitureResponse.class);
        return new ResponseEntity<VoitureResponse>(voitureResponse, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoiture(@PathVariable("id") long id){
        voitureService.deleteOneVoiture(id);
        return new ResponseEntity<String>("Voiture deleted", HttpStatus.GONE);
    }

    private ResponseEntity<List<VoitureResponse>> getListResponseEntity(List<VoitureDto> voitureDtos) {
        List<VoitureResponse> voitureResponses = new ArrayList<>();
        List<ImagesResponse> images ;
        for (VoitureDto voitureDto : voitureDtos){
            images= new ArrayList<>();
            VoitureResponse voitureResponse = mapper.map(voitureDto, VoitureResponse.class);
            for (ImagesDto imagesDto : voitureDto.getImages()){
                ImagesResponse imagesResponse = mapper.map(imagesDto, ImagesResponse.class);
                images.add(imagesResponse);
            }
            voitureResponse.setImages(images);
            voitureResponses.add(voitureResponse);
        }
        return new ResponseEntity<List<VoitureResponse>>(voitureResponses, HttpStatus.OK);
    }


}
