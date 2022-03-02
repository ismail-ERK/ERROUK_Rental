package com.example.location_voitures.dtos;

import com.example.location_voitures.entities.AgencesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoitureDto {
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
    private List<ImagesDto> images;

}
