package com.example.location_voitures.responses;

import com.example.location_voitures.dtos.ImagesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoitureResponse {
    private long id;
    private String numeroImmatriculation;
    private String couleur;
    private String marque;
    private String modele;
    private String transmission;
    private boolean disponibilite;
    private int nbrPortes;
    private int nbrPlaces;
    private double prixJour;
    private List<ImagesResponse> images;

}
