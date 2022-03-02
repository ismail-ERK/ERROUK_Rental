package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.ImagesDto;
import com.example.location_voitures.requests.images.ImagesResuest;
import com.example.location_voitures.responses.ImagesResponse;
import com.example.location_voitures.services.ImagesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    ImagesService imagesService;

    @GetMapping()
    public ResponseEntity<List<ImagesResponse>> getAllImages() {
        List<ImagesDto> imagesDtos = imagesService.getAllImages();
        List<ImagesResponse> imagesResponses = new ArrayList<>();
        for (ImagesDto imagesDto: imagesDtos){
            ImagesResponse imagesResponse = mapper.map(imagesDto, ImagesResponse.class);
            imagesResponses.add(imagesResponse);
        }
        return new ResponseEntity<List<ImagesResponse>>(imagesResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ImagesResponse>> getAllImagesByVoiture(@PathVariable("id") long id) {
        List<ImagesDto> imagesDtos = imagesService.getAllImagesOfVoiture(id);
        List<ImagesResponse> imagesResponses = new ArrayList<>();
        for (ImagesDto imagesDto: imagesDtos){
            ImagesResponse imagesResponse = mapper.map(imagesDto, ImagesResponse.class);
            imagesResponses.add(imagesResponse);
        }
        return new ResponseEntity<List<ImagesResponse>>(imagesResponses, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ImagesResponse> createImageOfVoiture(@PathVariable("id") long id, @RequestBody ImagesResuest imagesResuest) {
        ImagesDto imagesDto = mapper.map(imagesResuest, ImagesDto.class);
        ImagesDto imagesDto1 = imagesService.createOneImage(id, imagesDto);
        ImagesResponse imagesResponse = mapper.map(imagesDto1, ImagesResponse.class);
        return new ResponseEntity<ImagesResponse>(imagesResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagesResponse> updateImageOfVoiture(@PathVariable("id") long id, @RequestBody ImagesResuest imagesResuest) {
        ImagesDto imagesDto = mapper.map(imagesResuest, ImagesDto.class);
        ImagesDto imagesDto1 = imagesService.updateOneImage(id, imagesDto);
        ImagesResponse imagesResponse = mapper.map(imagesDto1, ImagesResponse.class);
        return new ResponseEntity<ImagesResponse>(imagesResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImageOfVoiture(@PathVariable("id") long id) {
        imagesService.deleteOneImage(id);

        return new ResponseEntity<String>("Image deleted", HttpStatus.ACCEPTED);
    }


}
